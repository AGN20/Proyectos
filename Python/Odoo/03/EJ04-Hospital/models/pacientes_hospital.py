# -*- coding: utf-8 -*-
from odoo import models, fields, api
from odoo.exceptions import ValidationError



class BaseArchive(models.AbstractModel):
    #Nombre y descripcion del modelo
    _name = 'base.archive'
    _description = 'Fichero abstracto'

    #Introduce el atributo "Activo"
    activo = fields.Boolean(default=True)

    #Introducice metodo "archivar" que invierte el estado de "activo"
    def archivar(self):
        for record in self:
            record.activo = not record.activo


#Definimos modelo pacientes hospital
class PacientesHospital(models.Model):

    #Nombre y descripcion del modelo
    _name = 'pacientes.hospital'
    #Hereda de "base.archive" (el modelo abstracto creado antes)
    _inherit = ['base.archive']

    _description = 'Pacientes del hospital'

    #Parametros de ordenacion por defecto
    _order = 'nombre'

    #ATRIBUTOS

    _rec_name = 'nombre'


    #Atributo nombre
    nombre = fields.Char('Nombre', required=True)

    apellido = fields.Char('Apellido', required=True)

    sintomas = fields.Html('Sintomas', sanitize=True, strip_style=False)

    diagnosticos = fields.Many2many("diagnostico.hospital")
    