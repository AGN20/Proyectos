from telegram.ext import Updater
from telegram.ext import CallbackContext
from telegram.ext import CommandHandler
from telegram import Update
import logging
import requests
import json


updater = Updater(token='5007867814:AAHI5PM5pW3PpJygcbkdgNSXFtziP1Ah_pw', use_context=True)
dispatcher = updater.dispatcher

#Comando para ejecutar
def start(update: Update, context: CallbackContext):
    context.bot.send_message(chat_id=update.effective_chat.id, text="Bot a la escucha, que necesita?")

start_handler = CommandHandler('start',start)
dispatcher.add_handler(start_handler)

#Comando para ayda
def help(update: Update, context: CallbackContext):
    context.bot.send_message(chat_id=update.effective_chat.id, text="Los comandos que se pueden utilizar son:"
    +"\n Consultar --> consultar 'numero del socio'"
    +"\n Ingresar --> crear 'nombre' 'apellido' 'numero del socio')"
    +"\n Modificar --> modificar 'nombre' 'apellido' 'numero del socio'"
    +"\n Borrar --> borrar 'numero del socio'"
    )

help_handler = CommandHandler('help',help)
dispatcher.add_handler(help_handler)

#Consultar
def consultar(update: Update, context: CallbackContext):
    chat_id = update.message.chat_id
    num_socio = int(context.args[0])
    resp = requests.get('http://localhost:8069/gestion/apirest/socio?data={"num_socio":"'+str(num_socio)+'"}')
    print(resp)
    print('http://localhost:8069/gestion/apirest/socio?data={"num_socio":"'+str(num_socio)+'"}')
    update.message.reply_text(resp.text)

consultar_handler = CommandHandler('consultar',consultar)
dispatcher.add_handler(consultar_handler)

#Modificar
def modificar(update: Update, context: CallbackContext):
    chat_id = update.message.chat_id
    num_socio = int(context.args[2])
    nombre = str(context.args[0])
    apellidos = str(context.args[1])
    resp = requests.put('http://localhost:8069/gestion/apirest/socio?data={"num_socio":"'+str(num_socio)+'","nombre":"'+str(nombre)+'","apellidos":"'+str(apellidos)+'"}')
    print(resp)
    print('http://localhost:8069/gestion/apirest/socio?data={"num_socio":"'+str(num_socio)+'","nombre":"'+str(nombre)+'","apellidos":"'+str(apellidos)+'"}')
    update.message.reply_text(resp.text)

modificar_handler = CommandHandler('modificar',modificar)
dispatcher.add_handler(modificar_handler)

#Crear
def crear(update: Update, context: CallbackContext):
    chat_id = update.message.chat_id
    num_socio = int(context.args[2])
    nombre = str(context.args[0])
    apellidos = str(context.args[1])
    resp = requests.post('http://localhost:8069/gestion/apirest/socio?data={"num_socio":"'+str(num_socio)+'","nombre":"'+str(nombre)+'","apellidos":"'+str(apellidos)+'"}')
    print(resp)
    print('http://localhost:8069/gestion/apirest/socio?data={"num_socio":"'+str(num_socio)+'","nombre":"'+str(nombre)+'","apellidos":"'+str(apellidos)+'"}')
    update.message.reply_text(resp.text)

crear_handler = CommandHandler('crear',crear)
dispatcher.add_handler(crear_handler)

#Borrar
def borrar(update: Update, context: CallbackContext):
    chat_id = update.message.chat_id
    num_socio = int(context.args[0])
    resp = requests.delete('http://localhost:8069/gestion/apirest/socio?data={"num_socio":"'+str(num_socio)+'"}')
    print(resp)
    print('http://localhost:8069/gestion/apirest/socio?data={"num_socio":"'+str(num_socio)+'"}')
    update.message.reply_text(resp.text)

borrar_handler = CommandHandler('borrar',borrar)
dispatcher.add_handler(borrar_handler)

#Poner el bot en funcionamiento
updater.start_polling()