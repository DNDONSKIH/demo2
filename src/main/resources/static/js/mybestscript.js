const addContactButton = document.querySelector("#show-add-contact-form");
const createContactForm = document.getElementById('create-contact-form')

addContactButton.addEventListener('click',function() {
    createContactForm.style.display = "flex";
})

createContactForm.addEventListener('click',function(event){
    if(event.target === this) { createContactForm.style.display = "none"; }
})

const findContactButton = document.querySelector("#show-find-contact-form");
const findContactForm = document.getElementById('find-contact-form')

findContactButton.addEventListener('click',function() {
    findContactForm.style.display = "flex";
})

findContactForm.addEventListener('click',function(event){
    if(event.target === this) {
        findContactForm.style.display = "none";
        //this.style.display = "none" //работает, но почему this в этом контексте указывает на div!???
    }
})

