import React from 'react';
import axios from "axios";
import { Redirect } from "react-router-dom";
import { Button, FormGroup, Container } from "reactstrap";


const url = "http://localhost:8585/login/";

class Login extends React.Component {

    state = {
        data: [],
        form: {
            correo: '',
            password: '',

        },
        redirige: false
    }

    peticionGet = (correo) => {
        axios.get(url + correo).then(response => {

            this.setState({ data: response.data });
            if (response.data.length === 0) {
                alert("datos incorrectos");
            } else {
                this.setState({
                    redirige: true
                })
            }

        }).catch(error => {
            console.log(error.message);
        })
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
    render() {
        const { form, redirige } = this.state;
        if (redirige) {
            return (<Redirect to={'/solicitar-libro/' + this.state.data.id} />)
        }
        return (
            <div>
                <Container>
                    <h1>Login</h1>
                    <FormGroup>
                        <label>Correo</label>
                        <input className="form-control" type="email" name="correo" id="correo" onChange={this.handleChange} />
                        <br />
                        <label>Password</label>
                        <input className="form-control" type="password" name="password" id="password" onChange={this.handleChange} />
                        <br />
                        <Button color="success" onClick={() => { this.peticionGet(form.correo) }} >enviar</Button>
                    </FormGroup>
                </Container>
            </div >
        );
    }
}
export default Login;