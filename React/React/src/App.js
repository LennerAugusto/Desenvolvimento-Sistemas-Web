
import {BrowserRouter, Routes,Route} from 'react-router-dom'
import './App.css';
import './styles/reset.css';
import React, { Fragment } from 'react';
import Incluir from './paginas/.Usuario/Incluir';
import Listar from './paginas/.Usuario/Listar';
import Rotas from './rotas/Rotas';

function App() {

  return (
    <Fragment>
      <BrowserRouter>
        <Routes>
          <Route element={<Rotas/>}>
          <Route path="/usuario/listar" element={<Listar/>} />
          <Route path="/usuario/incluir" element={<Incluir/>} />
          </Route>
          </Routes>
      </BrowserRouter>
    </Fragment>
  );
}

export default App;
