using DinkToPdf;
using DinkToPdf.Contracts;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.AuthorizeAttributes;
using WebApplication_Uitleendienst.Models.ViewModels;
using WebApplication_Uitleendienst.Models.ViewModels.Magazijns;
using WebApplication_Uitleendienst.Services.Interfaces;
using WebApplication_Uitleendienst.Utilities;

namespace WebApplication_Uitleendienst.Areas.MagazijnContact.Controllers {
    [Area("MagazijnContact")]
    public class MagazijnController : BaseController {
        private readonly IBaseService<ContactMagazijn> _contactMagazijnService;
        private readonly IBaseService<Persoon> _persoonService;
        private readonly IBaseService<Magazijn> _magazijnService;
        private readonly IBaseService<Uitlening> _uitleningsService;
        private readonly IBaseService<BeschikbaarItem> _beschikbaarItemService;
        private readonly IBaseService<UitleenbaarItem> _uitleenbaarItemService;
        private readonly IBaseService<Categorie> _categorieService;

        private IConverter _converter;

        public MagazijnController(IBaseService<Categorie> categorieService, IConverter converter, IBaseService<BeschikbaarItem> beschikbaarItemService, IBaseService<UitleenbaarItem> uitleenbaarItemService, IBaseService<Uitlening> UitleningService, IBaseService<ContactMagazijn> contactMagService, IBaseService<Persoon> persoonService, IBaseService<Magazijn> magazijnService) {
            _persoonService = persoonService;
            _contactMagazijnService = contactMagService;
            _magazijnService = magazijnService;
            _uitleningsService = UitleningService;
            _uitleenbaarItemService = uitleenbaarItemService;
            _beschikbaarItemService = beschikbaarItemService;
            _converter = converter;
            _categorieService = categorieService;
        }
        public IActionResult Index(MagazijnViewModel model = null) {
            if (model == null)
                model = new MagazijnViewModel();
            try {
                // retrieve current contactmag info
                var persoon = _persoonService.GetAll(propertyName: "username", propertyValue: UserInfo.Username, token: UserInfo.Token)?.FirstOrDefault();
                var contactmag = new ContactMagazijn();
                if (persoon != null) {
                    contactmag = _contactMagazijnService.GetAll(propertyName: "persoonId", propertyValue: persoon.Id, token: UserInfo.Token)?.FirstOrDefault();
                    if (contactmag != null) {
                        model.Magazijn = _magazijnService.Get(propertyValue: contactmag.magazijnId, token: UserInfo.Token);
                        model.Uitleningen =
                            _uitleningsService.GetAll(token: UserInfo.Token)?
                            .Where(u => u.MagazijnId == contactmag.magazijnId)
                            .ToList();
                    } else {
                        throw new Exception("Gebruiker is geen bestaande magazijnverantwoordelijke");
                    }

                    var beschikbareItems = _beschikbaarItemService.GetAll(token: UserInfo.Token)
                        .Where(s => s.MagazijnId == contactmag.magazijnId)
                        .Select(b => b.UitleenbaarItemId);

                    var uitleenbareItems = _uitleenbaarItemService.GetAll(token: UserInfo.Token);

                    model.Products = uitleenbareItems
                        .Where(s => beschikbareItems.Any(p => p.Equals(s.Id)))
                        .ToList();

                } else {
                    model.Message = "Geen geldige contactinformatie gevonden voor huidige gebruiker";
                    model.Level = InfoLevel.danger;
                }
            } catch (Exception ex) {
                model.Message = ex.Message;
                model.Level = InfoLevel.danger;
            }

            return View(model);
        }
        public IActionResult CreateUitleenbaarItem() {
            var model = new MagazijnViewModel();
            try {
                var categories = _categorieService.GetAll(token: UserInfo.Token);
                if (categories != null) {
                    var item = new UitleenbaarItem {
                        Categories = categories
                    };
                    return View(item);
                } else {
                    model.Message = $"Er zijn geen categorieën gevonden, gelieve deze eerst aan te maken alvorens een product te creëeren. <p><a href='{ Url.Action("Create", "Category", new { area = "Admin" }) }'>Aanmaken</a></p>";
                    model.Level = Models.ViewModels.InfoLevel.warning;
                    return RedirectToAction("Index", model);
                }
            } catch (Exception ex) {
                model.Message = ex.Message;
                model.Level = InfoLevel.danger;
                return RedirectToAction("index", model);
            }
        }

        public IActionResult CreateBeschikbaarItem(string id) {
            var model = new MagazijnViewModel();
            try {
                model.Product = _uitleenbaarItemService.Get(propertyValue: id, token: UserInfo.Token);
                // retrieve current contactmag info
                var persoon = _persoonService.GetAll(propertyName: "username", propertyValue: UserInfo.Username, token: UserInfo.Token)?.FirstOrDefault();
                var contactmag = new ContactMagazijn();
                if (persoon != null) {
                    contactmag = _contactMagazijnService.GetAll(propertyName: "persoonId", propertyValue: persoon.Id, token: UserInfo.Token)?.FirstOrDefault();
                    if (contactmag != null)
                        model.Magazijn = _magazijnService.Get(propertyValue: contactmag.magazijnId, token: UserInfo.Token);
                }
            } catch (Exception ex) {
                model.Message = ex.Message;
                model.Level = InfoLevel.danger;
                return RedirectToAction("index", model);
            }
            return View(model);
        }

        public IActionResult Detail(string Id) {
            var model = new MagazijnViewModel();
            try {
                var prod = _uitleenbaarItemService.Get(propertyValue: Id, token: UserInfo.Token);
                return View(prod);
            } catch (Exception ex) {
                model.Message = ex.Message;
                model.Level = InfoLevel.danger;
                return RedirectToAction("index", model);
            }
        }

        public IActionResult Edit(string Id) {
            var model = new MagazijnViewModel();
            try {
                var prod = _uitleenbaarItemService.Get(propertyValue: Id, token: UserInfo.Token);
                if (prod != null)
                    prod.Categories = _categorieService.GetAll(token: UserInfo.Token);
                return View(prod);
            } catch (Exception ex) {
                model.Message = ex.Message;
                model.Level = InfoLevel.danger;
                return RedirectToAction("index", model);
            }
        }


        [HttpPost]
        public async Task<IActionResult> EditPost(UitleenbaarItem prod) {
            var model = new MagazijnViewModel();
            try {
                var item = await _uitleenbaarItemService.Update(prod, customEntity: prod.Id, token: UserInfo.Token);
                if (item == null) {
                    model.Message = "Item kon niet worden geupdated.";
                    model.Level = Models.ViewModels.InfoLevel.danger;
                } else {
                    model.Message = "Item is succesvol geupdated.";
                    model.Level = Models.ViewModels.InfoLevel.success;
                }
            } catch (Exception ex) {
                model.Message = ex.Message;
                model.Level = InfoLevel.danger;
                return RedirectToAction("index", model);
            }
            return RedirectToAction("Index", model);
        }

        [HttpPost]
        public async Task<IActionResult> CreatePost(UitleenbaarItem prod) {
            var model = new MagazijnViewModel();
            try {
                var entity = await _uitleenbaarItemService.Save(prod, token: UserInfo.Token);
                if (entity == null) {
                    model.Message = "Item kon niet worden opgeslagen.";
                    model.Level = Models.ViewModels.InfoLevel.danger;
                } else {
                    model.Message = "Item is succesvol opgeslagen.";
                    model.Level = Models.ViewModels.InfoLevel.success;
                }
            } catch (Exception ex) {
                model.Message = ex.Message;
                model.Level = InfoLevel.danger;
                return RedirectToAction("index", model);
            }
            return RedirectToAction("Index", model);
        }


        public async Task<IActionResult> Delete(string id) {
            var model = new MagazijnViewModel();
            try {
                _uitleenbaarItemService.Delete(id, token: UserInfo.Token);
                model.Message = "Item is succesvol verwijderd.";
                model.Level = Models.ViewModels.InfoLevel.success;

            } catch (Exception ex) {
                model.Message = ex.Message;
                model.Level = Models.ViewModels.InfoLevel.danger;
            }
            return RedirectToAction("Index", model);
        }

        [HttpPost]
        public async Task<IActionResult> CreatePostBeschikbaar(MagazijnViewModel model) {
            try {
                var entity = await _beschikbaarItemService.Save(model.BeschikbaarItem, token: UserInfo.Token);
                if (entity == null) {
                    model.Message = "Beschikbaarheid kon niet worden opgeslagen.";
                    model.Level = Models.ViewModels.InfoLevel.danger;
                } else {
                    model.Message = "Beschikbaarheid is succesvol opgeslagen.";
                    model.Level = Models.ViewModels.InfoLevel.success;
                }
            } catch (Exception ex) {
                model.Message = ex.Message;
                model.Level = Models.ViewModels.InfoLevel.danger;
            }
            return RedirectToAction("Index", model);
        }

        public IActionResult CreatePDF(string uitleningId) {
            var model = new MagazijnViewModel();
            try {
                var uitlening = _uitleningsService.Get(propertyValue: uitleningId, token: UserInfo.Token);
                var items = _uitleenbaarItemService.GetAll("uitleningId", uitlening.Id, token: UserInfo.Token).ToList();
                var globalSettings = new GlobalSettings {
                    ColorMode = ColorMode.Color,
                    Orientation = Orientation.Portrait,
                    PaperSize = PaperKind.A4,
                    Margins = new MarginSettings { Top = 10 },
                    DocumentTitle = "Uitleningsdocument"
                };
                var objectSettings = new ObjectSettings {
                    PagesCount = true,
                    HtmlContent =
                    HtmlExtensions.GetPdfHeader() +
                    HtmlExtensions.GetHTMLString<Uitlening>(new List<Uitlening> { uitlening }, "Uitlening:", "padding-top:250px;font-size:40px;") +
                    HtmlExtensions.GetHTMLString<UitleenbaarItem>(items, "Producten in deze uitlening:", "padding-top:50px;font-size:40px;"),
                    WebSettings = { DefaultEncoding = "utf-8", UserStyleSheet = Path.Combine(Directory.GetCurrentDirectory(), "wwwroot/bundles/css/", "assets.min.css") },
                    HeaderSettings = { FontName = "Arial", FontSize = 9, Right = "Page [page] of [toPage]", Line = true },
                    FooterSettings = { FontName = "Arial", FontSize = 9, Line = true, Center = "©PastoryUitleendienst 2021-22" }
                };
                var pdf = new HtmlToPdfDocument() {
                    GlobalSettings = globalSettings,
                    Objects = { objectSettings }
                };

                var doc = _converter.Convert(pdf);
                return File(doc, "application/pdf");
            } catch (Exception ex) {
                model.Message = ex.Message;
                model.Level = Models.ViewModels.InfoLevel.danger;
            }
            return RedirectToAction("Index", model);
        }
    }
}
