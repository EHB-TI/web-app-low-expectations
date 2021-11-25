using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.Models.ViewModels.Catalogue;
using WebApplication_Uitleendienst.Services.Interfaces;

namespace WebApplication_Uitleendienst.Controllers {
    public class CatalogueController : BaseController {
        private readonly ILogger<HomeController> _logger;
        private readonly IBaseService<Categorie> _categorieService;
        private readonly IBaseService<BeschikbaarItem> _beschikbaarItemService;

        private IEnumerable<BeschikbaarItem> ItemList = new List<BeschikbaarItem>() {
            new BeschikbaarItem {
                AantalBeschikbaar = 25,
                AantalGereserveerd = 2,
                AantalTotaal = 27,
                Id = "25",
                Magazijn = null,
                UitleenbaarItem = new UitleenbaarItem {
                    Naam="Kabel",
                    Prijs= (float) 25.99,
                    Categorie = new Categorie {
                        Naam = "Geluid",
                        Image = "~/images/default.png"
                    }
                }
            },
             new BeschikbaarItem {
                AantalBeschikbaar = 25,
                AantalGereserveerd = 2,
                AantalTotaal = 27,
                Id = "25",
                Magazijn = null,
                UitleenbaarItem = new UitleenbaarItem {
                    Naam = "Juke",
                    Prijs= (float) 29.99,
                    Categorie = new Categorie {
                        Naam = "Geluid",
                        Image = "~/images/default.png"
                    }
                }
            },
             new BeschikbaarItem {
                AantalBeschikbaar = 25,
                AantalGereserveerd = 2,
                AantalTotaal = 27,
                Id = "25",
                Magazijn = null,
                UitleenbaarItem = new UitleenbaarItem {
                    Naam = "Top",
                    Prijs= (float) 155.99,
                    Categorie = new Categorie {
                        Naam = "Geluid",
                        Image = "~/images/default.png"
                    }
                }
            }
        };

        public CatalogueController(IBaseService<Categorie> catService, IBaseService<BeschikbaarItem> beschikbaarItemService) {
            _categorieService = catService;
            _beschikbaarItemService = beschikbaarItemService;
        }

        public IActionResult Catalogue(string categoryId) {
            var model = new CatalogueViewModel();
            // API / NOT IMPLEMENTED
            //model.Products = _beschikbaarItemService.GetAll("catId", categoryId);
            model.Products = ItemList;
            model.Categories = _categorieService.GetAll();
            return View(model);
        }
    }
}
