function total(n) {
    const priceId = document.getElementById("price");
    const price = parseInt(priceId.dataset.price);

    let quantity = document.querySelector(".quantity");
    let amount = parseInt(quantity.value);
    
    amount += n;
    
    if(amount < 1){
        amount = 1;
        alert("최소 구매수량은 1개입니다");
    }

    //총 금액 계산
    let tot = price * amount;

    quantity.value = amount;

    tot = tot.toString().replace(/\B(?=(\d{3})+(?!\d))/g , ",");
    document.querySelector(".total").innerHTML=`${tot}(${amount}개)`;

}
document.querySelector(".plus").addEventListener("click", function(){
    total(1);
});

document.querySelector(".minus").addEventListener("click", function(){
    total(-1);
});