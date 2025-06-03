<?php
// insertar.php

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $nombre = $_POST['nombre'] ?? '';
    $correo = $_POST['correo'] ?? '';

    // Aquí puedes guardar en la base de datos. Para este ejemplo, solo mostramos un mensaje.
    if (!empty($nombre) && !empty($correo)) {
        // Puedes conectar a base de datos aquí si deseas
        echo "Registro recibido: $nombre - $correo";
    } else {
        echo "Faltan datos";
    }
} else {
    echo "Método no permitido";
}
?>
