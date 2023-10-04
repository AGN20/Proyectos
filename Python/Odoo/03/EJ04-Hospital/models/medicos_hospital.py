# -*- coding: utf-8 -*-
from odoo import models, fields, api
from odoo.exceptions import ValidationError

#Definimos modelo medicos hospital
class MedicosHospital(models.Model):

    #Nombre y descripcion del modelo
    _name = 'medicos.hospital'
    #Hereda de "base.archive" (el modelo abstracto creado antes)
    _inherit = ['base.archive']

    _description = 'Medicos del hospital'

    #Parametros de ordenacion por defecto
    _order = 'nombre'

    #ATRIBUTOS

    _rec_name = 'codigo_colegiado'


    #Atributo nombre
    nombre = fields.Char('Nombre', required=True)

    apellido = fields.Char('Apellido', required=True)

    codigo_colegiado = fields.Char('Codigo Colegiado', required=True)

    diagnosticos = fields.Many2many("diagnostico.hospital")