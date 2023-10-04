# -*- coding: utf-8 -*-
from odoo import models, fields, api
from odoo.exceptions import ValidationError
from datetime import date, timedelta

class cliente(models.Model):
    #Nombre del model
    _name = 'cliente'
    _description = 'Modelo almacenar Clientes'
    #Queremos que ordene los repartidores por apellido
    _order = 'apellido'
    #Indicamos que si en otra vista se ve, muestre el codigo de repartidor
    _rec_name = "dni_cliente"

    #Elementos
    nombre = fields.Char('Nombre', required=True, index=True)
    apellido = fields.Char('Apellido', required=True, index=True)
    dni_cliente = fields.Char('DNI', Required=True, index=True)
    tel = fields.Integer('Numero de telefono', required=True, index=True)

    #Constraints de SQL del modelo
    _sql_constraints = [
        ('dni_cliente', 'UNIQUE (dni_cliente)', 'El dni del cliente deve ser unico.')
    ]

