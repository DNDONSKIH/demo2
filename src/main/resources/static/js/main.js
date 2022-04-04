$(function(){
    //Show add contact form
    $('#show-add-contact-form').click(function(){
        $('#create-contact-form').css('display', 'flex');
    });

    //Closing add contact form
    $('#create-contact-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Show find contact form
    $('#show-find-contact-form').click(function(){
        $('#find-contact-form').css('display', 'flex');
    });

    //Closing find contact form
    $('#find-contact-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });
});