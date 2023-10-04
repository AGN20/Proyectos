# -*- coding: utf-8 -*-
from odoo import http
from odoo.http import request

class estado_reparto(http.Controller):
    #http://localhost:8069/estado/<id_reparto>
    @http.route('/estado/<id>', auth='public', cors='*', type='http')
    def obtenerDatosSocios(self, id, **kw):
        # Obtenemos la referencia del modelo (pensado en este programa para ser "socios")
        repartos = request.env['reparto'].sudo().search([])

        for r in repartos:
                estatus = r.estado[0]
            
        if estatus == "1":
            return "El paquete no ha salido"
        elif estatus == "2":
            return "El paquete esta en camino"
        else:
            return "El paquete a sido entregado"