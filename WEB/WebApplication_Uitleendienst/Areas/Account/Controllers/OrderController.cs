using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.Areas.Account.Models.ViewModels;
using WebApplication_Uitleendienst.Services.Interfaces;

namespace WebApplication_Uitleendienst.Areas.Account {
    [Area("Account")]
    [Authorize]

    public class OrderController : BaseController {
        private readonly IBaseService<UitleenbaarItem> _uitleenbaarItemService;
        private readonly IBaseService<Magazijn> _magazijnService;
        private readonly IBaseService<Uitlening> _uitleningService;
        private readonly IBaseService<UitleningItem> _uitleningItemService;
        private readonly IBaseService<Persoon> _persoonService;
        public OrderController(IBaseService<UitleenbaarItem> UitleenbaarItemService, IBaseService<UitleningItem> uitleningItemServicen, IBaseService<Magazijn> MagazijnService, IBaseService<Uitlening> UitleningService, IBaseService<Persoon> persoonService) {
            _uitleenbaarItemService = UitleenbaarItemService;
            _magazijnService = MagazijnService;
            _uitleningService = UitleningService;
            _uitleningItemService = uitleningItemServicen;
            _persoonService = persoonService;
        }
        public IActionResult Index() {
            var model = new OrderViewModel();
            // retrieve persoon based on current User
            var persoon = _persoonService.GetAll("username", UserInfo.Username, token: UserInfo.Token)?.FirstOrDefault();
            if (persoon != null) {
                // retrieve uitleningen for user
                model.Uitleningen = _uitleningService.GetAll("persoonId", persoon.Id, token: UserInfo.Token)?.ToList();
                model.Uitleningen.ForEach(u => {
                    u.Magazijn = _magazijnService.Get(propertyValue: u.MagazijnId, token: UserInfo.Token);
                });

            } else {
                model.Message = "Geen informatie van gebruiker gevonden";
                model.Level = WebApplication_Uitleendienst.Models.ViewModels.InfoLevel.info;
            }
            return View(model);
        }

        public IActionResult Detail(string id) {
            var model = new OrderViewModel();
            model.UitleningItems = _uitleningItemService.GetAll("uitleningId", id, token: UserInfo.Token).ToList();
            if (model.UitleningItems != null) {
                model.UitleningItems.ForEach(u => {
                    u.UitleenbaarItem = _uitleenbaarItemService.Get(propertyValue: u.uitleenbaarItemId, token: UserInfo.Token);
                });
            }
            return View(model);
        }
    }
}
