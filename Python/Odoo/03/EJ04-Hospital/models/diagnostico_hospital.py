# -*- coding: utf-8 -*-
from odoo import models, fields, api
from odoo.exceptions import ValidationError

#Definimos modelo pacientes hospital
class PacientesHospital(models.Model):

    #Nombre y descripcion del modelo
    _name = 'diagnostico.hospital'
    #Hereda de "base.archive" (el modelo abstracto creado antes)
    _inherit = ['base.archive']

    _description = 'Diagnosticos del hospital'

    #Parametros de ordenacion por defecto
    _order = 'id'

    #ATRIBUTOS

    _rec_name = 'id'


    #Atributo nombre
    id = fields.Char('ID', required=True, index=True)

    codigo_Medico = fields.Many2many("medicos.hospital")

    codigo_Name = fields.Many2many("pacientes.hospital")

    diagnostico = fields.Html('Diagnostico', sanitize=True, strip_style=False)