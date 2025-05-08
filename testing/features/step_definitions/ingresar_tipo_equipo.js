const assert = require('assert');
const { Given, When, Then } = require('cucumber');
const request = require('sync-request');

Given('que se ingresa el tipo de equipo con {string}', function (nombre) {
    this.nombre = nombre;
});

When('guardo un tipo de equipo', function () {
    const newTipoEquipo = {
        nombre: this.nombre
    };

    const postResponse = request('POST', 'http://backend:8080/tipos-equipo', {
        json: newTipoEquipo
    });

    this.response = {
        status: postResponse.statusCode,
        message: postResponse.getBody('utf8')
    };
    console.log(this.response.message);
});

Then('se espera el siguiente {int} con la {string}', function (expectedStatus, expectedMessage) {
    assert.strictEqual(this.response.status, expectedStatus);

    const parsedResponse = JSON.parse(this.response.message);
    const actualMessage =  `Tipo de equipo ${parsedResponse.data.nombre} registrado correctamente`;
    assert.strictEqual(actualMessage, expectedMessage);
});