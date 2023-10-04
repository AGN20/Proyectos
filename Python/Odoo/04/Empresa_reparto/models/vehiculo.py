# -*- coding: utf-8 -*-
from odoo import models, fields, api
from odoo.exceptions import ValidationError
from datetime import date, timedelta

class vehiculo(models.Model):
    #Nombre del model
    _name = 'vehiculo'
    _description = 'Modelo almacenar vehiculos'
    #Queremos que ordene los repartidores por apellido
    _order = 'matricula'
    #Indicamos que si en otra vista se ve, muestre el codigo de repartidor
    _rec_name = "matricula"

    #Elementos
    tipo = fields.Selection(
        [('1', 'Ciclomotor'),
        ('2', 'Furgoneta'),
        ('3', 'Bicicleta')],
        'Tipo de vehiculo', default="3")
    matricula = fields.Char('Matricula', index=True)
    descripcion = fields.Char('Descripcion')
    foto_vehiculo = fields.Image('Foto vehiculo', max_width=100, max_height=100)
    ocupado = fields.Boolean(default=False)

    #Constraints de SQL del modelo
    _sql_constraints = [
        ('matricula_uniq', 'UNIQUE (matricula)', 'La matricula debe ser unica.')
    ]

