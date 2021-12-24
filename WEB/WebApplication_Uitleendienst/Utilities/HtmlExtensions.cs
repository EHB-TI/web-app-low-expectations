using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WebApplication_Uitleendienst.Utilities {
    public static class HtmlExtensions {
        public static string GetPdfHeader() {
            var sb = new StringBuilder();
            sb.AppendLine($@"
              <div class='container'>
                <div class='row'>
                     <div class='col-12'>
                        <div class='info' style='float:left;'>
                            <img style='width:250px;' class='img-responsive' src='{Path.Combine(Directory.GetCurrentDirectory(), "wwwroot/images/logo/logo.png")}'/>
                            <p>Pastorielaan 22</p>
                            <p>Hoboken 2210</p>
                            <p>Oost-Vlaanderen</p>
                        </div>
                        <div class='scan' style='float:right;'>
                            <img style='width:150px;float:right;' class='img-responsive' src='{Path.Combine(Directory.GetCurrentDirectory(), "wwwroot/images/pdf/qr-code.png")}'/>
                        </div>
                     </div>
                </div>      
            </div>
            ");
            return sb.ToString();
        }
        public static string GetHTMLString<T>(IList<T> data, string title = "", string parentCSS = "") {
            var sb = new StringBuilder();
            PropertyDescriptorCollection properties =
                TypeDescriptor.GetProperties(typeof(T));

            sb.AppendLine($@"
                <div class='container' style='{parentCSS}'>
                <div class='row'>
                <div class='col-12'>
                <h4 style='font-weight:900:font-size:25px'>{title}</h4>
                <table class='table table-striped'>
                <thead class='thead-dark'>
                        <tr>");

            foreach (PropertyDescriptor prop in properties)
                sb.AppendLine($" <th scope='col'>{prop.Name} </th>");

            sb.Append(@"</tr></thead><tbody>");

            foreach (T item in data) {
                sb.Append("<tr>");
                foreach (PropertyDescriptor prop in properties)
                    sb.AppendLine($"<td>{prop.GetValue(item)?.ToString()}</td>");
                sb.Append("</tr>");
            }
            sb.Append(@"
                </tbody>
                </table>
                </div>
                </div>
                </div>");

            return sb.ToString();
        }
    }
}
