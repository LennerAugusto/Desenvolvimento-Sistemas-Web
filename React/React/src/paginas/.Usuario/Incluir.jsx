import React, {Fragment} from "react";

const Incluir = () =>{
    return(
        <Fragment>
            <div className="col-xs-12 col-sm-12 col-md-12">
                <div>
                    <form className="mt-3">
                        <div className="row  mb-3">
                            <div className="col-xs-12 col-sm-12 col-md-12">
                                <div className="from-group">
                                    <label htmlFor="username" className="control-label">Nome</label>
                                    <input type="text"
                                           id="username"
                                           name="username"
                                           className="form-control"/>
                                </div>
                            </div>
                        </div>
                        <div className="row  mb-3">
                            <div className="col-xs-12 col-sm-12 col-md-12">
                                <div className="from-group">
                                    <label htmlFor="email" className="control-label">E-mail:</label>
                                    <input type="text"
                                           id="email"
                                           name="email"
                                           className="form-control"/>
                                </div>
                            </div>
                        </div>
                        <div className="row  mb-3">
                            <div className="col-xs-12 col-sm-12 col-md-12">
                                <div className="from-group">
                                    <label htmlFor="password" className="control-label">Senha:</label>
                                    <input type="password"
                                           id="password"
                                           name="password"
                                           className="form-control"/>
                                </div>
                            </div>
                        </div>
                        <div className="row  mb-3">
                            <div className="col-xs-12 col-sm-12 col-md-12">
                                <div className="from-group">
                                    <label htmlFor="confirmPassword" className="control-label">Confirmar Senha:</label>
                                    <input type="password"
                                           id="confirmPassword"
                                           name="confirmPassword"
                                           className="form-control"/>
                                </div>
                            </div>
                        </div>
                        <div>
                            <button type="submit" className="btn btn-success">Salvar</button>
                            <a href="#" type="button" className="btn btn-warning">Cancelar</a>
                        </div>
                    </form>
                </div>
            </div>
        </Fragment>
    )
}

export default Incluir