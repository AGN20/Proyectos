from odoo import models, fields, api
from odoo.exceptions import ValidationError

#Definimos modelo pacientes hospital
class InstitutoAlumno(models.Model):

    #Nombre y descripcion del modelo
    _name = 'instituto.alumno'
    #Hereda de "base.archive" (el modelo abstracto creado antes)
    _inherit = ['base.archive']

    _description = 'Modulos'

    #Parametros de ordenacion por defecto
    _order = 'id'

    #ATRIBUTOS

    _rec_name = 'id'


    #Atributo nombre
    id = fields.Char('ID', required=True, index=True)

    nombre = fields.Char('Nombre', required=True)

    apellido = fields.Char('apellido', required=True)

    modulo = fields.Many2many("instituto.modulo")