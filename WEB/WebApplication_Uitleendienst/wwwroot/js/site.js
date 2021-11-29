// Please see documentation at https://docs.microsoft.com/aspnet/core/client-side/bundling-and-minification
// for details on configuring this project to bundle and minify static web assets.

// Write your JavaScript code.

function AddToCart($elem, $url) {

    var $detailParent = $elem.parents('.detail');
    var $productId = $detailParent.find('.product-id').val();
    var $magazijn = $('input[name="magazijn"]:checked').val();
    var $amount = $detailParent.find('.amount select').val();

    var $data = {
        productId: $productId,
        magazijnId: $magazijn,
        amount: $amount
    };

    $.ajax({
        url: $url,
        type: 'POST',
        data: $data,
        success: function (data) {
            var $infoText = $detailParent.find(".info-text");
            if (data == "not_authenticated") {
                $infoText.removeClass("hidden");
                $infoText.find("label").text("Gelieve je aan te melden/registreren om producten toe te voegen aan het winkelmandje.");
            } else if (data == "success") {
                $infoText.removeClass("hidden");
                $infoText.find("label").text("Succesvol toegevoegd aan het winkelmandje.");
                UpdateCart();
            }
        },
        error: function () {
            alert("error");
        }
    });
};

function UpdateCart() {
   
}

