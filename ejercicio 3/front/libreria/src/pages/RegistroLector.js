import React from 'react';
import axios from "axios";


import { Button, FormGroup, Container } from "reactstrap";


class RegistroLector extends React.Component {

    state = {
        data: [],
        form: {
            nombre: '',
            correo: '',
            fecha_nacimiento: '',
            password: ''
        }
    }

    handleChange = async e => {
        e.persist();
        await this.setState({
            form: {
                ...this.state.form,
                [e.target.name]: e.target.value
            }
        });
        console.log(this.state.form);
    }

    peticionPost = async () => {
        await axios.post("http://localhost:8585/registrarse", this.state.form).then(response => {
            alert(response.data);
        }).catch(error => {
            console.log(error.message);
        })
    }

    render() {
        return (
            <div>
                <Container>
                    <FormGroup>
                        <label>Nombre</label>
                        <br />
                        <input className="form-control" type="text" name="nombre" id="nombre" onChange={this.handleChange} />
                        <br />
                        <label>Correo</label>
                        <br />
                        <input className="form-control" type="email" name="correo" id="correo" onChange={this.handleChange} />
                        <br />
                        <label>Fecha Nacimiento</label>
                        <br />
                        <input className="form-control" type="text" name="fecha_nacimiento" id="fecha_nacimiento" onChange={this.handleChange} />
                        <br />
                        <label>Password</label>
                        <br />
                        <input className="form-control" type="password" id="passsword" onChange={this.handleChange} />
                        <br />
                        <Button color="success" onClick={() => { this.peticionPost() }} >Registrarse</Button>
                    </FormGroup>
                </Container>
            </div>
        );
    }
}

export default RegistroLector;