// Create a class Animal with a constructor setting the name property. Then, create a subclass dog that extends Animal and adds a bark method which prints dog name.(use prototype-based inheritance)

function Animal(name){
    this.name = name;
}

function Dog(name){
    Animal.call(this, name);
}

Dog.__proto__ = Object.create(Animal.__proto__);
Dog.__proto__.constructor = Dog;

Dog.prototype.bark = function(){
    console.log(this.name + " is barking !");
}

const myDog = new Dog("Tommy");
myDog.bark();
