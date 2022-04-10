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
const contactListContainer = document.querySelector("#contact-list");
const addContactAjaxButton = document.querySelector("#show-create-contact-form-ajax-container-button");
const createContactAjaxContainer = document.getElementById('create-contact-form-ajax-container')

const newContactHTMLGenerator = (result) => {
    const newContactId = result['id'];
    const newContactSurname = result['surname'];
    const newContactMiddleName = result['middleName'];

    return `<div>
                <a href="/contacts/${newContactId}">${newContactSurname} ${newContactMiddleName}</a><br>
                <form action="/contacts" method="post">
                    <input type="hidden" name="_method" value="DELETE"/>
                    <input type="hidden" name="contactId" value="${newContactId}">
                    <p><input type="submit" value="Удалить"/></p>
                </form><hr>
            </div>`;
}

const addContactViaAjax = async () => {
    const newContactForm = new FormData(formWithData);
    // for (let [key, value] of newContactForm.entries()) {  console.log(key, value); }

    try {
        const response = await fetch('/contactlist', {
            method: 'POST',
            body: newContactForm
        });
        const result = await response.json();

        const operationSuccess = result['id'] < 0? false : true;
        if(operationSuccess) {
            const newContactHTML = newContactHTMLGenerator(result);
            contactListContainer.insertAdjacentHTML('beforeend', newContactHTML)
            createContactAjaxContainer.style.display = "none";
        }
        else {
            alert("Ошибка! Проверьте формат введенных данных.")
        }
        console.log('SUCCESS:', JSON.stringify(result));
    } catch (error) {
        console.error('ERROR:', error);
    }
}

fetchTestButton.addEventListener('submit',function(event){
    event.preventDefault();
    addContactViaAjax();
})

addContactAjaxButton.addEventListener('click',function() {
    createContactAjaxContainer.style.display = "flex";
})

createContactAjaxContainer.addEventListener('click',function(event){
    if(event.target === this) { this.style.display = "none"; }
})
