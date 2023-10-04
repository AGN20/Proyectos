# -*- coding: utf-8 -*-
from odoo import http
from odoo.http import request
import json
# --ACTIVIDAD 01--
#Clase del controlador web
class eliminarempates(http.Controller):
    #http://localhost:8069/ligafutbol/equipo/eliminarempates/
    @http.route('/ligafutbol/equipo/eliminarempates/', type='http', auth='none')
    def eliminarempates(self):
        #Obtenemos la referencia al modelo de Equipo
        partidos = request.env['liga.partido'].sudo().search([])
        numeroPartidosEliminados = 0

        for partido in partidos:
             if partido.goles_casa == partido.goles_fuera:
                numeroPartidosEliminados = numeroPartidosEliminados + 1
                partido.unlink()
        return "Partidos empatados eliminados: " + str(numeroPartidosEliminados)
                
        
