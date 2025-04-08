import http from '../config/http';

const listagemUsuario = async() =>{
    return(
        http({
            method: 'GET',
            url: '/usuario/listar',
        }).then((response) => {
            return response?.data
        })
    )
}
const lerUsuarioPorId = async(id) =>{
    return(
        http({
            method: 'GET',
            url: `/usuario/buscar/${id}`,
        }).then((response) => {
            return response?.data
        })
    )
}

export{
    listagemUsuario,
    lerUsuarioPorId,
}