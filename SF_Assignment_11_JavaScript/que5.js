// Create a function to mutate an array with filter out values specified. (Hint : Use filter(), includes(), push()) const filteredArray = (originalArray, removeValuesArray) => {//} Note – Use online javascript compiler to run programe like - https://playcode.io/javascript-compiler


const filteredArray = (originalArray, removeValuesArray) => {
    // Create a new array with elements that are not in removeValuesArray.
    const filtered = originalArray.filter(item => !removeValuesArray.includes(item));
  
    // Clear the original array.
    originalArray.length = 0;
  
    // Push the filtered elements back into the original array.
    filtered.forEach(item => originalArray.push(item));
  
    return originalArray;
  };
  
  let nums = [1, 2, 3, 4, 5];
  let remove = [2, 4, 6];
  filteredArray(nums, remove);
  console.log(nums);