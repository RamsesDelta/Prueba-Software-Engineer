import { BrowserRouter, Switch, Route } from 'react-router-dom'
import "bootstrap/dist/css/bootstrap.min.css";
import Usuarios from './pages/Usuarios'
import Libros from './pages/Libros'
import SolicitarLibro from './pages/SolicitarLibro'
import HistorialLibros from './pages/HistorialLibros'
import RegistroLector from './pages/RegistroLector'
import Login from './pages/Login'
import Solicitudes from './pages/Solicitudes'


function App() {
  return (
    <div >
      <BrowserRouter>
        <Switch>
          <Route path='/usuarios' component={Usuarios} />
          <Route path='/libros' component={Libros} />
          <Route path='/solicitar-libro/:id' component={SolicitarLibro} />
          <Route path='/historial-prestado/:id' component={HistorialLibros} />
          <Route path='/registro-lector' component={RegistroLector} />
          <Route path='/login' component={Login} />
          <Route path='/solicitudes' component={Solicitudes} />
        </Switch>
      </BrowserRouter>
    </div>
  );
}

export default App;
