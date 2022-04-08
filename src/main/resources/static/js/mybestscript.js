const addContactButton = document.querySelector("#show-add-contact-form");
const createContactForm = document.getElementById('create-contact-form')
const findContactButton = document.querySelector("#show-find-contact-form");
const findContactForm = document.getElementById('find-contact-form')
const formWithData = document.querySelector('#add-contact-form-ajax')
const fetchTestButton = document.querySelector("#add-contact-form-ajax");

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

const testPost = async () => {
    const newContactForm = new FormData(formWithData);
    // for (let [key, value] of newContactForm.entries()) {  console.log(key, value); }

    try {
        const response = await fetch('/contacts/addnew', {
            method: 'POST',
            body: newContactForm
        });
        const result = await response.json();
        console.log('SUCCESS:', JSON.stringify(result));
    } catch (error) {
        console.error('ERROR:', error);
    }
}

fetchTestButton.addEventListener('submit',function(event){
    event.preventDefault();
    testPost();
})



