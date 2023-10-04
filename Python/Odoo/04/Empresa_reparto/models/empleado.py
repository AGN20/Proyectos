# -*- coding: utf-8 -*-
from odoo import models, fields, api
from odoo.exceptions import ValidationError
from datetime import date, timedelta

class empleado(models.Model):
    #Nombre del model
    _name = 'empleado'
    _description = 'Modelo almacenar Empleados'
    #Queremos que ordene los repartidores por apellido
    _order = 'apellido'
    #Indicamos que si en otra vista se ve, muestre el codigo de repartidor
    _rec_name = 'cr'

    #Elementos
    cr = fields.Char('Codigo repartidor', required=True, index=True)

    nombre = fields.Char('Nombre', required=True, index=True)
    apellido = fields.Char('Apellido', required=True, index=True)
    dni = fields.Char('DNI', Required=True, index=True)

    tel = fields.Integer('Numero de telefono', required=True, index=True)

    foto = fields.Image('Foto repartidor', max_width=100, max_height=100)

    carnet = fields.Selection(
        [('1', 'Ciclomotor'),
        ('2', 'Furgoneta'),
        ('3', 'Ciclomotor y Furgoneta'),
        ('4', 'Sin carnet')],
        'Carnet', default="4")

    #Repartos que tiene la persona
    list_repartos = fields.One2many('reparto', 'repartidor')

    ocupado = fields.Boolean(default=False)

    #Constraints de SQL del modelo
    _sql_constraints = [
        ('cr_uniq', 'UNIQUE (cr)', 'El codigo de repartidor deve ser unico.'),
        ('dni_uniq', 'UNIQUE (dni)', 'El dni de repartidor deve ser unico.')
    ]
