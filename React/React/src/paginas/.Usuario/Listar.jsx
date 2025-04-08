import React, { Fragment, useEffect, useState } from "react";
import { DEFAULT_IMAGEM_THUMBNAIL } from "../../config/config";
import * as FaIcons from 'react-icons/fa';
import * as BsIcons from 'react-icons/bs';
import { listagemUsuario } from "../../service/UsuarioService";

const Listar = () => {

    const [usuario, setUsuario] = useState([])

    useEffect ( () => {

        async function loadUser(){
            const response = await listagemUsuario()
                .catch( (error)=> {
                    console.log(error);
                });
            setUsuario(response)
        }
        loadUser();
        
    },[])
    const onChangeUsuario = () =>{
        
    }

    return (
        <Fragment>
            <div className="row">
                <div className="col-xs-12 col-sm-12col-md-12">
                 <div className="app-windows">
                    <div>
                        <div className="row justify-content-center">
                            
                            <form>
                                <div className="row mb-3">
                                    <label htmlFor="key" className="col-form-label">Filtro:</label>
                                    <div className="col-xs-11 col-sm-11 col-md-6">
                                        <input type="text" id="key" name="key"
                                            className="form-control" />
                                    </div>
                                    <div className="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                                        <button type="submit" className="btn btn-primary form-control">Consulta</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div id="tabela">
                            <table className="table table-striped table-bordered app-tabela">
                                <thead>
                                <tr className="bg-success text-white app-cabecalho-tabela">
                                    <th>Foto</th>
                                    <th >Id </th>
                                    <th>Nome</th>
                                    <th>E-mail </th>
                                    <th>Ativo</th>
                                    <th>Ações</th>
                                </tr>
                                </thead>
                                <tbody>
                                    
                                        {
                                            usuario&&
                                                usuario.map( (usuario) => (
                                                <tr className="app-coluna-detalhe-centro" key={usuario}>
                                                  <th>
                                                    <img
                                                    src={
                                                        usuario.foto === ''
                                                                ? DEFAULT_IMAGEM_THUMBNAIL
                                                                : `${SERVIDOR_GET_IMAGEM}${usuario.foto}`
                                                    }
                                                    alt="foto usuario"
                                                    className="img-avatar"
                                                    />
                                                  </th>
                                                    <tr>{usuario.id}</tr>
                                                    <td>{usuario.usarname}</td>
                                                    <td>{usuario.email}</td>
                                                    <td>
                                                        <input type="checkbox" className="btn-check" name="{`ativo_${usuario.id}`}" id="ativo" autocomplete="off" checked/>
                                                        {usuario.ativo ? (
                                                            <label className="btn btn-outline-success" htmlFor={`ativo_${usuario.id}`}><i><BsIcons.BsPersonFillCheck/></i></label>
                                                        ) : (
                                                            <label className="btn btn-outline-danger" htmlFor={`ativo_${usuario.id}`}><i><BsIcons.BsPersonFillLock/></i></label>
                                                        )}
                                                    </td>
                                                <td>
                                                    <a href="#" type="button" className="btn bnt-secondary"><i><FaIcons.FaPencilAlt/></i></a>
                                                    <a href="#" type="button" className="btn btn-danger"><i><FaIcons.FaTrashAlt/></i></a>
                                                    <a href="# " type="button" className="btn btn-info"><i><FaIcons.FaSearchPlus/></i></a>
                                                </td>   
                                                </tr>         
                                                ))
                                        }
                                </tbody>
                            </table>
                            <a href="#" type="button"className="btn btn-primary">
                                Cadastrar novo usuario
                                <i><FaIcons.FaSave/></i>
                            </a>
                            
                        </div>
                    </div>
                 </div>
                </div>
            </div>

        </Fragment>
    )

}

export default Listar;