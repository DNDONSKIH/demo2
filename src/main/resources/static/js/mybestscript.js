
const addContactButton = document.querySelector("#show-add-contact-container-button");
const createContactContainer = document.getElementById('create-contact-container')
addContactButton.addEventListener('click',function() {
    createContactContainer.style.display = "flex";
})

createContactContainer.addEventListener('click',function(event){
    if(event.target === this) { this.style.display = "none"; }
})

const findContactButton = document.querySelector("#show-find-contact-container-button");
const findContactContainer = document.getElementById('find-contact-container')

findContactButton.addEventListener('click',function() {
    findContactContainer.style.display = "flex";
})

findContactContainer.addEventListener('click',function(event){
    if(event.target === this) {this.style.display = "none";}
})

const formWithData = document.querySelector('#add-contact-form-ajax')
const fetchTestButton = document.querySelector("#add-contact-form-ajax");

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


