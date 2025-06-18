// 3.Write a function multiplyByEight, Note: Use Closures, don't return 8*x directly.

function multiplyByEight(){
    const multiplier = 8;
    
    return function(x) {
        return x * multiplier;
    };
}

const timeEight = multiplyByEight();
console.log(timeEight(5));
