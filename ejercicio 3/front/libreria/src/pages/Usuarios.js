import React from 'react';
import axios from "axios";

import { Table, Button, Container, Modal, ModalHeader, ModalBody, ModalFooter, } from "reactstrap";

const url = "http://localhost:8282/listar-usuarios";

class Usuarios extends React.Component {

    state = {
        data: [],
        modalInsertar: false,
        modalEliminar: false,
        mostrar: true,
        form: {
            id: '',
            nombre: '',
            correo: '',
            enable: '',
            password: '',
            fecha_nacimiento: '',
            rol: '1',
            nombre_rol: ''
        },
        dataRol: [],
        fecha: new Date(),

    }


    peticionGet = () => {
        axios.get(url).then(response => {
            this.setState({ data: response.data });
        }).catch(error => {
            console.log(error.message);
        })
    }

    peticionGetRol = () => {
        axios.get("http://localhost:8282/obtener-roles").then(response => {
            this.setState({ dataRol: response.data });
        }).catch(error => {
            console.log(error.message);
        })
    }

    seleccionarUsuario = (usuario) => {
        this.setState({
            tipoModal: 'actualizar',
            form: {
                id: usuario.id,
                nombre: usuario.nombre,
                correo: usuario.correo,
                fecha_nacimiento: usuario.fecha_nacimiento,
                enable: usuario.enable,
                rol: usuario.rol

            }
        })
    }

    modalInsertar = () => {
        this.setState({ modalInsertar: !this.state.modalInsertar });
    }

    peticionPost = async () => {
        delete this.state.form.id;
        await axios.post("http://localhost:8282/insertar-usuario", this.state.form).then(response => {
            this.modalInsertar();
            this.peticionGet();
            alert(response.data);
        }).catch(error => {
            console.log(error.message);
        })
    }

    peticionPut = () => {
        axios.put("http://localhost:8282/actulizar-usuario/" + this.state.form.id, this.state.form).then(response => {
            this.modalInsertar();
            this.peticionGet();
        })
    }

    peticionDelete = () => {
        axios.delete("http://localhost:8282/eliminar-usuario/" + this.state.form.id).then(response => {
            this.setState({ modalEliminar: false });
            this.peticionGet();
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
        this.peticionGetRol();
    }

    render() {
        const { form } = this.state;

        return (
            <div>
                <Container>
                    <Button color="success" onClick={() => { this.setState({ form: null, tipoModal: 'insertar' }); this.modalInsertar() }}>Agregar</Button>
                    <Table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Correo</th>
                                <th>Fecha Nacimiento</th>
                                <th>Habilitado</th>
                                <th>Rol</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.data.map(usuario => {
                                return (
                                    <tr>
                                        <td>{usuario.id}</td>
                                        <td>{usuario.nombre}</td>
                                        <td>{usuario.correo}</td>
                                        <td>{usuario.fecha_nacimiento}</td>
                                        <td>{usuario.enable}</td>
                                        <td>{usuario.nombre_rol}</td>
                                        <td>
                                            <Button color="primary" onClick={() => { this.setState({ mostrar: false }); this.seleccionarUsuario(usuario); this.modalInsertar() }}>editar</Button>
                                            {"   "}
                                            <Button color="danger" disabled={usuario.enable === 2 ? true : false} onClick={() => { this.seleccionarUsuario(usuario); this.setState({ modalEliminar: true }) }}> eliminar</Button>
                                        </td>
                                    </tr>
                                )
                            })}
                        </tbody>
                    </Table>
                </Container>
                <Modal isOpen={this.state.modalInsertar}>
                    <Modal isOpen={this.state.modalInsertar}>
                        <ModalHeader style={{ display: 'block' }}>
                            <span onClick={() => this.modalInsertar()}>x</span>
                        </ModalHeader>
                        <ModalBody>
                            <div className="form-group">
                                <label htmlFor="id">ID</label>
                                <input className="form-control" type="text" name="id" id="id" readOnly onChange={this.handleChange} value={form ? form.id : '0'} />
                                <br />
                                <label htmlFor="nombre">Nombre</label>
                                <input className="form-control" type="text" name="nombre" id="nombre" onChange={this.handleChange} value={form ? form.nombre : ''} />
                                <br />
                                <label htmlFor="correo">Correo</label>
                                <input className="form-control" type="text" name="correo" id="correo" onChange={this.handleChange} value={form ? form.correo : ''} />
                                <br />
                                <label htmlFor="fecha_nacimiento">Fecha Nacimiento</label>
                                <input className="form-control" type="text" name="fecha_nacimiento" id="fecha_nacimiento" onChange={this.handleChange} value={form ? form.fecha_nacimiento : ''} />
                                <br />
                                <label htmlFor="password" >Contraseña</label>
                                <input className="form-control" type="text" name="password" id="password" onChange={this.handleChange} value={form ? form.password : ''} />
                                <br />
                                <label htmlFor="enable">Habilitado</label>
                                <input className="form-control" type="text" name="enable" id="enable" onChange={this.handleChange} value={form ? form.enable : ''} />
                                <br />
                                <label htmlFor="rol">Rol</label>

                                <select onChange={this.handleChange} name="rol" value={form ? form.rol : 1}>
                                    {this.state.dataRol.map(rol => {
                                        return (<option value={rol.id} > {rol.nombre} </option>)
                                    })}
                                </select>
                            </div>
                        </ModalBody>

                        <ModalFooter>
                            {this.state.tipoModal === 'insertar' ?
                                <Button color="success" onClick={() => this.peticionPost()}>
                                    Insertar
                      </Button> : <Button color="success" onClick={() => this.peticionPut()}>
                                    Actualizar
                      </Button>
                            }
                            <Button className="btn btn-danger" onClick={() => this.modalInsertar()}>Cancelar</Button>
                        </ModalFooter>
                    </Modal>
                </Modal>
                <Modal isOpen={this.state.modalEliminar}>
                    <ModalBody>
                        Estás seguro que deseas dar debaja al usuario {form && form.nombre}
                    </ModalBody>
                    <ModalFooter>
                        <Button className="btn btn-danger" onClick={() => this.peticionDelete()}>Sí</Button>
                        <Button className="btn btn-secundary" onClick={() => this.setState({ modalEliminar: false })}>No</Button>
                    </ModalFooter>
                </Modal>
            </div>
        );
    }
}

export default Usuarios;