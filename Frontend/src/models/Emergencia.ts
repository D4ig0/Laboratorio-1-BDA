export default interface Emergencia {
    id: number,
    institucion: string,
    nombre: string,
    descripcion: string,
    fecha_inicio: string,
    fecha_termino: string,
    activo: boolean,
    tareas_activas: number
}