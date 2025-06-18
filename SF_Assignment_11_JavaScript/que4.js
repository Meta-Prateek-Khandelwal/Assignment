// 4.Create a function waitAndReturn that returns a Promise which resolves with the string "Resolved" after 5 seconds. Then, use async/await to call this function and log the result.

function waitAndReturn() {
    return new Promise((resolve) => {
      setTimeout(() => {
        resolve("Resolved");
      }, 5000);
    });
  }
  
  async function example() {
    const result = await waitAndReturn();
    console.log(result);
  }
  
  example();