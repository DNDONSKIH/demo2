const addContactButton = document.querySelector("#show-add-contact-form");
const createContactForm = document.getElementById('create-contact-form')
const findContactButton = document.querySelector("#show-find-contact-form");
const findContactForm = document.getElementById('find-contact-form')

addContactButton.addEventListener('click',function() {
    createContactForm.style.display = "flex";
})

createContactForm.addEventListener('click',function(event){
    if(event.target === this) { this.style.display = "none"; }
})

findContactButton.addEventListener('click',function() {
    findContactForm.style.display = "flex";
})

findContactForm.addEventListener('click',function(event){
    if(event.target === this) {this.style.display = "none";}
})

