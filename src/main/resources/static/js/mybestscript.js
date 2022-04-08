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





// const fetchTestButton = document.querySelector("#fetch-post-button");
//
// fetchTestButton.addEventListener('click',function(event){
//     event.preventDefault();
//     testPost();
// })


const fetchTestButton = document.querySelector("#add-contact-form-ajax");

fetchTestButton.addEventListener('submit',function(event){
    event.preventDefault();
    testPost();
})

//const formWithData = document.getElementById('add-contact-form-ajax')
const formWithData = document.querySelector('#add-contact-form-ajax')

const testPost = async () => {

    const newContactForm = new FormData(formWithData);
    for (let [key, value] of newContactForm.entries()) {
        console.log(key, value);
    }

    try {
        const response = await fetch('/contacts/addnew', {
            method: 'POST',
            body: newContactForm
        });
        const result = await response.json();
        console.log('Успех:', JSON.stringify(result));
    } catch (error) {
        console.error('Ошибка:', error);
    }
}






