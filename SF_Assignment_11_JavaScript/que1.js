// Write a function called findMin that accepts a variable number of arguments and returns the smallest argument. Make sure to do this using the rest and spread operator.

function findMin(...number){
    let min = Number.MAX_VALUE;
    
    for(let num of number){
        if(min > num){
            min = num;
        }
    }
    
    return min;
}

console.log(findMin(5,4,3,2));
