#pragma checksum "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Views\Catalogue\Detail.cshtml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "1d8459b4b3884a89a2b655eefd634e5fc7da098e"
// <auto-generated/>
#pragma warning disable 1591
[assembly: global::Microsoft.AspNetCore.Razor.Hosting.RazorCompiledItemAttribute(typeof(AspNetCore.Views_Catalogue_Detail), @"mvc.1.0.view", @"/Views/Catalogue/Detail.cshtml")]
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
#line 1 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Views\_ViewImports.cshtml"
using WebApplication_Uitleendienst;

#line default
#line hidden
#nullable disable
#nullable restore
#line 2 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Views\_ViewImports.cshtml"
using WebApplication_Uitleendienst.Models;

#line default
#line hidden
#nullable disable
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"1d8459b4b3884a89a2b655eefd634e5fc7da098e", @"/Views/Catalogue/Detail.cshtml")]
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"1ab746905ea3fa988cf24fd1600e3321dc35c0c6", @"/Views/_ViewImports.cshtml")]
    public class Views_Catalogue_Detail : global::Microsoft.AspNetCore.Mvc.Razor.RazorPage<WebApplication_Uitleendienst.Models.ViewModels.Catalogue.CatalogueDetailViewModel>
    {
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_0 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("src", new global::Microsoft.AspNetCore.Html.HtmlString("~/images/default.png"), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
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
        private global::Microsoft.AspNetCore.Mvc.Razor.TagHelpers.UrlResolutionTagHelper __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper;
        private global::Microsoft.AspNetCore.Mvc.TagHelpers.OptionTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper;
        #pragma warning disable 1998
        public async override global::System.Threading.Tasks.Task ExecuteAsync()
        {
            WriteLiteral("\r\n");
#nullable restore
#line 3 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Views\Catalogue\Detail.cshtml"
  
    Layout = "~/Views/Shared/_Layout.cshtml";
    var count = 0;

#line default
#line hidden
#nullable disable
            WriteLiteral("<div class=\"container\">\r\n\r\n    ");
#nullable restore
#line 9 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Views\Catalogue\Detail.cshtml"
Write(await Component.InvokeAsync("Message", @Model));

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n\r\n");
#nullable restore
#line 11 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Views\Catalogue\Detail.cshtml"
     if (Model.Product != null) {

#line default
#line hidden
#nullable disable
            WriteLiteral("<div class=\"wrapper row detail\">\r\n            <input type=\"hidden\"");
            BeginWriteAttribute("value", " value=\"", 347, "\"", 372, 1);
#nullable restore
#line 12 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Views\Catalogue\Detail.cshtml"
WriteAttributeValue("", 355, Model.Product.Id, 355, 17, false);

#line default
#line hidden
#nullable disable
            EndWriteAttribute();
            WriteLiteral(@" class=""product-id"" />
            <div class=""hidden info-text alert alert-warning"">
                <label type=""text""></label>
            </div>
            <div class=""preview col-md-6"">

                <div class=""preview-pic tab-content"">
                    <div class=""tab-pane active"" id=""pic-1"">");
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("img", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.SelfClosing, "1d8459b4b3884a89a2b655eefd634e5fc7da098e5764", async() => {
            }
            );
            __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.Razor.TagHelpers.UrlResolutionTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper);
            __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_0);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            WriteLiteral("</div>\r\n                    <div class=\"tab-pane\" id=\"pic-2\">");
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("img", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.SelfClosing, "1d8459b4b3884a89a2b655eefd634e5fc7da098e6857", async() => {
            }
            );
            __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.Razor.TagHelpers.UrlResolutionTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper);
            __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_0);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            WriteLiteral("</div>\r\n                    <div class=\"tab-pane\" id=\"pic-3\">");
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("img", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.SelfClosing, "1d8459b4b3884a89a2b655eefd634e5fc7da098e7950", async() => {
            }
            );
            __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.Razor.TagHelpers.UrlResolutionTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper);
            __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_0);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            WriteLiteral("</div>\r\n                    <div class=\"tab-pane\" id=\"pic-4\">");
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("img", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.SelfClosing, "1d8459b4b3884a89a2b655eefd634e5fc7da098e9043", async() => {
            }
            );
            __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.Razor.TagHelpers.UrlResolutionTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper);
            __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_0);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            WriteLiteral("</div>\r\n                    <div class=\"tab-pane\" id=\"pic-5\">");
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("img", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.SelfClosing, "1d8459b4b3884a89a2b655eefd634e5fc7da098e10136", async() => {
            }
            );
            __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.Razor.TagHelpers.UrlResolutionTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper);
            __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_0);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            WriteLiteral("</div>\r\n                </div>\r\n                <ul class=\"preview-thumbnail nav nav-tabs\">\r\n                    <li class=\"active\"><a data-target=\"#pic-1\" data-toggle=\"tab\">");
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("img", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.SelfClosing, "1d8459b4b3884a89a2b655eefd634e5fc7da098e11351", async() => {
            }
            );
            __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.Razor.TagHelpers.UrlResolutionTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper);
            __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_0);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            WriteLiteral("</a></li>\r\n                    <li><a data-target=\"#pic-2\" data-toggle=\"tab\">");
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("img", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.SelfClosing, "1d8459b4b3884a89a2b655eefd634e5fc7da098e12461", async() => {
            }
            );
            __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.Razor.TagHelpers.UrlResolutionTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper);
            __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_0);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            WriteLiteral("</a></li>\r\n                    <li><a data-target=\"#pic-3\" data-toggle=\"tab\">");
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("img", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.SelfClosing, "1d8459b4b3884a89a2b655eefd634e5fc7da098e13571", async() => {
            }
            );
            __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.Razor.TagHelpers.UrlResolutionTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper);
            __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_0);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            WriteLiteral("</a></li>\r\n                    <li><a data-target=\"#pic-4\" data-toggle=\"tab\">");
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("img", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.SelfClosing, "1d8459b4b3884a89a2b655eefd634e5fc7da098e14681", async() => {
            }
            );
            __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.Razor.TagHelpers.UrlResolutionTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper);
            __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_0);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            WriteLiteral("</a></li>\r\n                    <li><a data-target=\"#pic-5\" data-toggle=\"tab\">");
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("img", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.SelfClosing, "1d8459b4b3884a89a2b655eefd634e5fc7da098e15791", async() => {
            }
            );
            __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.Razor.TagHelpers.UrlResolutionTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper);
            __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_0);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            WriteLiteral("</a></li>\r\n                </ul>\r\n\r\n            </div>\r\n            <div class=\"details col-md-6\">\r\n                <h3 class=\"product-title\">");
#nullable restore
#line 35 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Views\Catalogue\Detail.cshtml"
                                     Write(Model.Product.Naam);

#line default
#line hidden
#nullable disable
            WriteLiteral("</h3>\r\n                <div class=\"stock\">\r\n");
#nullable restore
#line 37 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Views\Catalogue\Detail.cshtml"
                     if (Model.BeschikbareItems != null) {

#line default
#line hidden
#nullable disable
            WriteLiteral("                        <h6><span class=\"font-weight-bold\">Totale stock: </span> ");
#nullable restore
#line 38 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Views\Catalogue\Detail.cshtml"
                                                                            Write(Model.TotalStock);

#line default
#line hidden
#nullable disable
            WriteLiteral("</h6>\r\n                        <h6 class=\"font-weight-bold\">Kies een magazijn: </h6>\r\n");
#nullable restore
#line 40 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Views\Catalogue\Detail.cshtml"
                         foreach (var magazijn in Model.Magazijnen) {
                            

#line default
#line hidden
#nullable disable
#nullable restore
#line 41 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Views\Catalogue\Detail.cshtml"
                             if (magazijn != null) {

#line default
#line hidden
#nullable disable
            WriteLiteral("                                <label class=\"w-100\">\r\n                                    <input type=\"radio\" ");
#nullable restore
#line 43 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Views\Catalogue\Detail.cshtml"
                                                    Write(count == 0 ? "checked='checked'" : "");

#line default
#line hidden
#nullable disable
            WriteLiteral(" name=\"magazijn\" value=\"");
#nullable restore
#line 43 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Views\Catalogue\Detail.cshtml"
                                                                                                                   Write(magazijn.Id);

#line default
#line hidden
#nullable disable
            WriteLiteral("\" />\r\n                                    ");
#nullable restore
#line 44 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Views\Catalogue\Detail.cshtml"
                               Write(magazijn.Naam);

#line default
#line hidden
#nullable disable
            WriteLiteral(" : ");
#nullable restore
#line 44 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Views\Catalogue\Detail.cshtml"
                                                 Write(Model.BeschikbareItems.Where(s => s.MagazijnId == magazijn.Id).FirstOrDefault().AantalBeschikbaar > 0 ? Html.Raw("<span class='beschikbaar'>Beschikbaar</span>") : Html.Raw("<span class='niet-beschikbaar'Niet beschikaar</span>"));

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n                                </label>\r\n");
#nullable restore
#line 46 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Views\Catalogue\Detail.cshtml"
                                count++;
                            }

#line default
#line hidden
#nullable disable
#nullable restore
#line 47 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Views\Catalogue\Detail.cshtml"
                             
                        }

#line default
#line hidden
#nullable disable
#nullable restore
#line 48 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Views\Catalogue\Detail.cshtml"
                         
                    } else {

#line default
#line hidden
#nullable disable
            WriteLiteral("                        <h6><span class=\"font-weight-bold\">Dit item is momenteel niet op stock.</span></h6>\r\n");
#nullable restore
#line 51 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Views\Catalogue\Detail.cshtml"
                    }

#line default
#line hidden
#nullable disable
            WriteLiteral("                    <div class=\"amount\">\r\n                        <h6 class=\"font-weight-bold\">Aantal: </h6>\r\n                        <select class=\"form-control\" name=\"select-amount\">\r\n                            ");
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("option", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "1d8459b4b3884a89a2b655eefd634e5fc7da098e22300", async() => {
                WriteLiteral("1");
            }
            );
            __Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.OptionTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper);
            BeginWriteTagHelperAttribute();
            __tagHelperStringValueBuffer = EndWriteTagHelperAttribute();
            __tagHelperExecutionContext.AddHtmlAttribute("selected", Html.Raw(__tagHelperStringValueBuffer), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.Minimized);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            WriteLiteral("\r\n                            ");
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("option", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "1d8459b4b3884a89a2b655eefd634e5fc7da098e23593", async() => {
                WriteLiteral("2");
            }
            );
            __Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.OptionTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            WriteLiteral("\r\n                            ");
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("option", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "1d8459b4b3884a89a2b655eefd634e5fc7da098e24575", async() => {
                WriteLiteral("3");
            }
            );
            __Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.OptionTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            WriteLiteral("\r\n                            ");
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("option", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "1d8459b4b3884a89a2b655eefd634e5fc7da098e25557", async() => {
                WriteLiteral("4");
            }
            );
            __Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.OptionTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            WriteLiteral("\r\n                            ");
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("option", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "1d8459b4b3884a89a2b655eefd634e5fc7da098e26539", async() => {
                WriteLiteral("5");
            }
            );
            __Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.OptionTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            WriteLiteral(@"
                        </select>
                    </div>
                </div>
                <p class=""product-description"">Suspendisse quos? Tempus cras iure temporibus? Eu laudantium cubilia sem sem! Repudiandae et! Massa senectus enim minim sociosqu delectus posuere.</p>
                <h4 class=""price"">Prijs: <span>€");
#nullable restore
#line 64 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Views\Catalogue\Detail.cshtml"
                                           Write(Model.Product.Prijs);

#line default
#line hidden
#nullable disable
            WriteLiteral("</span></h4>\r\n                <div class=\"action\">\r\n                    <a href=\"#!\" ");
#nullable restore
#line 66 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Views\Catalogue\Detail.cshtml"
                             Write(Model.BeschikbareItems == null ? "disabled" : "");

#line default
#line hidden
#nullable disable
            WriteLiteral(" class=\"add-to-cart m-0 btn btn-dark ");
#nullable restore
#line 66 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Views\Catalogue\Detail.cshtml"
                                                                                                                     Write(Model.BeschikbareItems == null ? "disabled" : "");

#line default
#line hidden
#nullable disable
            WriteLiteral("\" onclick=\"AddToCart($(this), \'");
#nullable restore
#line 66 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Views\Catalogue\Detail.cshtml"
                                                                                                                                                                                                      Write(Url.Action("AddToCart", "Catalogue"));

#line default
#line hidden
#nullable disable
            WriteLiteral("\')\">Toevoegen aan winkelmandje</a>\r\n                    <button ");
#nullable restore
#line 67 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Views\Catalogue\Detail.cshtml"
                        Write(Model.BeschikbareItems == null ? "disabled" : "");

#line default
#line hidden
#nullable disable
            WriteLiteral(" class=\"like btn btn-dark ");
#nullable restore
#line 67 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Views\Catalogue\Detail.cshtml"
                                                                                                     Write(Model.BeschikbareItems == null ? "disabled" : "");

#line default
#line hidden
#nullable disable
            WriteLiteral("\" type=\"button\"><span class=\"fa fa-heart\"></span></button>\r\n                </div>\r\n            </div>\r\n        </div>\r\n");
#nullable restore
#line 71 "C:\Users\michael.bracke\Documents\Personal\EHB\Software_Security\web-app-low-expectations\WEB\WebApplication_Uitleendienst\Views\Catalogue\Detail.cshtml"
    }

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n</div>\r\n\r\n");
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
        public global::Microsoft.AspNetCore.Mvc.Rendering.IHtmlHelper<WebApplication_Uitleendienst.Models.ViewModels.Catalogue.CatalogueDetailViewModel> Html { get; private set; }
    }
}
#pragma warning restore 1591
