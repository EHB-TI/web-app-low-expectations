#pragma checksum "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Areas\Account\Views\Order\Detail.cshtml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "575501a171c2039b6295fcf184d96facf21daf94"
// <auto-generated/>
#pragma warning disable 1591
[assembly: global::Microsoft.AspNetCore.Razor.Hosting.RazorCompiledItemAttribute(typeof(AspNetCore.Areas_Account_Views_Order_Detail), @"mvc.1.0.view", @"/Areas/Account/Views/Order/Detail.cshtml")]
namespace AspNetCore
{
    #line hidden
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Threading.Tasks;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.AspNetCore.Mvc.Rendering;
    using Microsoft.AspNetCore.Mvc.ViewFeatures;
#nullable restore
#line 1 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Areas\_ViewImports.cshtml"
using WebApplication_Uitleendienst;

#line default
#line hidden
#nullable disable
#nullable restore
#line 2 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Areas\_ViewImports.cshtml"
using WebApplication_Uitleendienst.Models;

#line default
#line hidden
#nullable disable
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"575501a171c2039b6295fcf184d96facf21daf94", @"/Areas/Account/Views/Order/Detail.cshtml")]
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"1ab746905ea3fa988cf24fd1600e3321dc35c0c6", @"/Areas/_ViewImports.cshtml")]
    public class Areas_Account_Views_Order_Detail : global::Microsoft.AspNetCore.Mvc.Razor.RazorPage<WebApplication_Uitleendienst.Areas.Account.Models.ViewModels.OrderViewModel>
    {
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_0 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("asp-area", "Account", global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_1 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("asp-controller", "Order", global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_2 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("asp-action", "Index", global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_3 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("src", new global::Microsoft.AspNetCore.Html.HtmlString("~/images/default.png"), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        #line hidden
        #pragma warning disable 0649
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperExecutionContext __tagHelperExecutionContext;
        #pragma warning restore 0649
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperRunner __tagHelperRunner = new global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperRunner();
        #pragma warning disable 0169
        private string __tagHelperStringValueBuffer;
        #pragma warning restore 0169
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperScopeManager __backed__tagHelperScopeManager = null;
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperScopeManager __tagHelperScopeManager
        {
            get
            {
                if (__backed__tagHelperScopeManager == null)
                {
                    __backed__tagHelperScopeManager = new global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperScopeManager(StartTagHelperWritingScope, EndTagHelperWritingScope);
                }
                return __backed__tagHelperScopeManager;
            }
        }
        private global::Microsoft.AspNetCore.Mvc.TagHelpers.AnchorTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper;
        private global::Microsoft.AspNetCore.Mvc.Razor.TagHelpers.UrlResolutionTagHelper __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper;
        #pragma warning disable 1998
        public async override global::System.Threading.Tasks.Task ExecuteAsync()
        {
            WriteLiteral("\r\n");
#nullable restore
#line 3 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Areas\Account\Views\Order\Detail.cshtml"
  
    ViewData["Title"] = "Account";
    Layout = "~/Areas/Account/Views/Shared/_Layout.cshtml";

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n<div class=\"container uitleningen\">\r\n    <div class=\"row\">\r\n        <div>\r\n            ");
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("a", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "575501a171c2039b6295fcf184d96facf21daf945348", async() => {
                WriteLiteral("Back");
            }
            );
            __Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.AnchorTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper);
            __Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper.Area = (string)__tagHelperAttribute_0.Value;
            __tagHelperExecutionContext.AddTagHelperAttribute(__tagHelperAttribute_0);
            __Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper.Controller = (string)__tagHelperAttribute_1.Value;
            __tagHelperExecutionContext.AddTagHelperAttribute(__tagHelperAttribute_1);
            __Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper.Action = (string)__tagHelperAttribute_2.Value;
            __tagHelperExecutionContext.AddTagHelperAttribute(__tagHelperAttribute_2);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            WriteLiteral("\r\n        </div>\r\n    </div>\r\n");
#nullable restore
#line 14 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Areas\Account\Views\Order\Detail.cshtml"
     if (Model.UitleningItems != null) {

#line default
#line hidden
#nullable disable
            WriteLiteral(@"        <div class=""row headers"">
            <div class=""col-4"">
                Product
            </div>
            <div class=""col-3"">
                Naam
            </div>
            <div class=""col-2"">
                Aantal
            </div>
            <div class=""col-2"">
                Prijs
            </div>
            <div class=""col-1"">

            </div>
        </div>
");
#nullable restore
#line 32 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Areas\Account\Views\Order\Detail.cshtml"
         foreach (var item in Model.UitleningItems) {

#line default
#line hidden
#nullable disable
            WriteLiteral("            <div class=\"row content\">\r\n                <div class=\"col-4\">\r\n                    ");
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("img", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.SelfClosing, "575501a171c2039b6295fcf184d96facf21daf948115", async() => {
            }
            );
            __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.Razor.TagHelpers.UrlResolutionTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper);
            __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_3);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            WriteLiteral("\r\n                </div>\r\n                <div class=\"col-3\">\r\n                    ");
#nullable restore
#line 38 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Areas\Account\Views\Order\Detail.cshtml"
               Write(item.UitleenbaarItem.Naam);

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n                </div>\r\n                <div class=\"col-2\">\r\n                    ");
#nullable restore
#line 41 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Areas\Account\Views\Order\Detail.cshtml"
               Write(item.aantal);

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n                </div>\r\n                <div class=\"col-2\">\r\n                    ");
#nullable restore
#line 44 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Areas\Account\Views\Order\Detail.cshtml"
               Write(item.Total);

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n                </div>\r\n                <div class=\"col-1\">\r\n                    <a");
            BeginWriteAttribute("href", " href=\"", 1403, "\"", 1455, 2);
            WriteAttributeValue("", 1410, "/Catalogue/Detail?Id=", 1410, 21, true);
#nullable restore
#line 47 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Areas\Account\Views\Order\Detail.cshtml"
WriteAttributeValue("", 1431, item.UitleenbaarItem.Id, 1431, 24, false);

#line default
#line hidden
#nullable disable
            EndWriteAttribute();
            WriteLiteral(">\r\n                        <i class=\"fa-solid fa-eye\"></i>\r\n                    </a>\r\n                </div>\r\n            </div>\r\n");
#nullable restore
#line 52 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Areas\Account\Views\Order\Detail.cshtml"
        }

#line default
#line hidden
#nullable disable
#nullable restore
#line 52 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Areas\Account\Views\Order\Detail.cshtml"
         
    } else {

#line default
#line hidden
#nullable disable
            WriteLiteral("        <div class=\"row\">\r\n            <div class=\"col-12 d-flex justify-content-center\">\r\n                <h4>Er zijn geen items teruggevonden op deze uitlening.</h4>\r\n            </div>\r\n        </div>\r\n");
#nullable restore
#line 59 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Areas\Account\Views\Order\Detail.cshtml"
    }

#line default
#line hidden
#nullable disable
            WriteLiteral("</div>\r\n\r\n\r\n\r\n\r\n");
        }
        #pragma warning restore 1998
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.ViewFeatures.IModelExpressionProvider ModelExpressionProvider { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IUrlHelper Url { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IViewComponentHelper Component { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IJsonHelper Json { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IHtmlHelper<WebApplication_Uitleendienst.Areas.Account.Models.ViewModels.OrderViewModel> Html { get; private set; }
    }
}
#pragma warning restore 1591
