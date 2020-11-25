import React from 'react';
import axios from "axios";

import { Table, Button, Container, Modal, ModalHeader, ModalBody, ModalFooter, } from "reactstrap";

const url = "http://localhost:8686/consultar-solicitudes/";

class Solicitudes extends React.Component {

    state = {
        data: [],
        form: {
            id: '',
            autor: '',
            titulo: '',
            nombre_lector: '',
            correo: '',
            status: ''
        },
        typeStatus: '1'
    }

    peticionGet = () => {
        axios.get(url + this.state.typeStatus).then(response => {
            this.setState({ data: response.data });
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

    componentDidMount() {
        this.peticionGet();
    }

    peticionPut = (id_solicitud, id_status) => {
        axios.put("http://localhost:8686/actulizar-solicitudes/" + Number(id_solicitud) + "/status/" + Number(id_status)).then(response => {
            alert(response.data)
            this.peticionGet();
        })
    }

    chnageType = (type_status) => {
        this.setState({
            typeStatus: type_status
        }
        )
        this.peticionGet();
    }

    render() {
        return (
            <div>
                <Container>
                    <Button color="success" onClick={() => { this.chnageType(1) }} >Solicitudes</Button>
                    {"   "}
                    <Button color="success" onClick={() => { this.chnageType(2) }}>Prestados</Button>
                    {"   "}
                    <Button color="success" onClick={() => { this.chnageType(3) }}>Canceladas</Button>
                    {"   "}
                    <Button color="success" onClick={() => { this.chnageType(4) }}>Devueltos</Button>
                    {"   "}
                    <Table >
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Autor</th>
                                <th>Titulo</th>
                                <th>Nombre Lector</th>
                                <th>Correo</th>
                                <th>Status</th>
                                <th>Accion</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.data.map(solicitud => {
                                return (
                                    <tr>
                                        <td>{solicitud.id}</td>
                                        <td>{solicitud.nombre_libro_autor}</td>
                                        <td>{solicitud.nombre_libro}</td>
                                        <td>{solicitud.nombre_lector}</td>
                                        <td>{solicitud.correo}</td>
                                        <td>{solicitud.status}</td>
                                        <td>
                                            <Button color="success" onClick={() => { this.peticionPut(solicitud.id, 2) }} >Aprobar</Button>
                                            {"   "}
                                            <Button className="btn btn-danger" onClick={() => { this.peticionPut(solicitud.id, 3) }} >Cancelar</Button>
                                            {"   "}
                                            <Button color="primary" onClick={() => { this.peticionPut(solicitud.id, 4) }} >Devolver</Button>
                                            {"   "}
                                        </td>
                                    </tr>
                                )
                            })}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default Solicitudes;