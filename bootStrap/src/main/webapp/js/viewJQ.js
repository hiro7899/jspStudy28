$(function(){
    const total = function(n) {
        const priceId = document.getElementById("price");
        const price = parseInt(priceId.dataset.price);
        let amount = $(".quantity").attr("value");
        amount = parseInt(amount);
        amount = amount + n;

        if(amount < 1){
            amount=1;
            alert("최소 구매수량은 1개 입니다");
        }

        let tot = price * amount;
        $(".quantity").attr("value", amount);
        const regexp = /\B(?=(\d{3})+(?!\d))/g;
        tot = tot.toString().replace(regexp, ',');

        $(".total").html(tot + "(" + amount + "개)");
    }

    $(".plus").on("click", function(){
        total(1);
    });

    $(".minus").on("click", function(){
        total(-1);
    });
});