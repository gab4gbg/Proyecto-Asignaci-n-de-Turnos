const tablaAdmin = document.querySelector("#tablaAdmin tbody");

if (tablaAdmin) {
    let turnos = JSON.parse(localStorage.getItem("turnos")) || [];

    turnos.sort((a, b) => b.prioridad - a.prioridad);

    turnos.forEach((t, index) => {
        const fila = document.createElement("tr");

        fila.innerHTML = `
            <td>${t.nombre}</td>
            <td>${t.prioridad ? "Sí" : "No"}</td>
            <td><button onclick="eliminarTurno(${index})">Eliminar</button></td>
        `;

        tablaAdmin.appendChild(fila);
    });
}

// Eliminar turno
function eliminarTurno(index) {
    let turnos = JSON.parse(localStorage.getItem("turnos")) || [];

    turnos.splice(index, 1);

    localStorage.setItem("turnos", JSON.stringify(turnos));

    location.reload();
}

// Limpiar todos los turnos
function limpiarTurnos() {
    localStorage.removeItem("turnos");
    location.reload();
}