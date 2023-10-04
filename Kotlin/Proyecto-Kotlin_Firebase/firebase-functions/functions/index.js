/**
 * Import function triggers from their respective submodules:
 *
 * const {onCall} = require("firebase-functions/v2/https");
 * const {onDocumentWritten} = require("firebase-functions/v2/firestore");
 *
 * See a full list of supported triggers at https://firebase.google.com/docs/functions
 */

// const {onRequest} = require("firebase-functions/v2/https");
// const logger = require("firebase-functions/logger");

// Create and deploy your first functions
// https://firebase.google.com/docs/functions/get-started

// exports.helloWorld = onRequest((request, response) => {
//   logger.info("Hello logs!", {structuredData: true});
//   response.send("Hello from Firebase!");
// });

const functions = require("firebase-functions");
const admin = require("firebase-admin");
admin.initializeApp();

exports.enviarNotificacion = functions.firestore
    .document("/obras/{obraId}")
    .onCreate(async (snapshot, context) => {
      const obra = snapshot.data();
      const usuarioId = obra.uid;

      const usuarioSnapshot = await admin.firestore()
          .doc(`/usuarios/${usuarioId}`).get();
      const usuario = usuarioSnapshot.data();

      const payload = {
        notification: {
          title: "El usuario " + usuario.nombre + " ha subido una nueva obra",
          body: "Se ha agregado la obra " + obra.nombre,
        },
      };

      return admin.messaging().sendToTopic(usuarioId, payload)
          .then((response) => {
            console.log("Notificación enviada con éxito:", response);
            return null;
          })
          .catch((error) => {
            console.error("Error al enviar la notificación:", error);
            return null;
          });
    });
