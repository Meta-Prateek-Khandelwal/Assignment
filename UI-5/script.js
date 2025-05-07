let btn = document.querySelectorAll(".collapsable");

btn.forEach(button => {
    button.addEventListener("click", () => {
        const nextSibling = button.nextElementSibling;
        if (nextSibling.style.display === "flex") {
            nextSibling.style.display = "none";
        } else {
            nextSibling.style.display = "flex"
        }
    });
})


const width = window.innerWidth;

window.onload = () => {
    if (width > 700) {
        document.getElementById("side").classList.remove("position-absolute")
    } else {
        document.getElementById("side").classList.add("position-absolute")
    }
}

const hamberger = document.getElementById("ham-menu");

hamberger.addEventListener("click", () => {
    document.getElementById("side").classList.remove("d-none")
})

const closeHam = document.getElementById("side-close");

closeHam.addEventListener("click", () => {
    document.getElementById("side").classList.add("d-none")
    document.getElementById("side").classList.add("position-absolute")
})