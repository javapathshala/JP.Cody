function validUser() {
    // Check survey name entered - always required
    var username = $('#j_username').val();
    if (username === null || username === '' || username === ' ') {
        $('#j_username').validationEngine('showPrompt', 'Username is required.', 'error', 'bottomLeft', true);
        return false;
    }

    // Check survey name entered - always required
    var password = $('#j_password').val();
    if (password === null || password === '' || password === ' ') {
        $('#j_password').validationEngine('showPrompt', 'Password is required.', 'error', 'bottomLeft', true);
        return false;
    }
    return true;
}

$(document).ready(function () {
    $('#j_username').focus();
    $('input').blur(function () {
        $('body').validationEngine('hideAll');
    });
    $('input').mouseout(function () {
        $('body').validationEngine('hideAll');
    });
    $('button').blur(function () {
        $('body').validationEngine('hideAll');
    });
    $('button').mouseout(function () {
        $('body').validationEngine('hideAll');
    });
    $('#login_button').click(function (e) {
        if (!validUser()) {
            e.preventDefault();
        }
    });
});