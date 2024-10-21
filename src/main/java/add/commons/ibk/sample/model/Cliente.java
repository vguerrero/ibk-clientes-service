package add.commons.ibk.sample.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Container(containerName = "mycollection")
public class Cliente {
    @Id
    @PartitionKey
    private String id;

    @NotBlank(message = "Por favor provea el nombre del cliente")
    private String nombre;

    @NotBlank(message = "Por favor provea el apellido del cliente")
    private String apellido;

    @NotNull(message = "El apellido materno no puede ser nulo")
    private String apellidoMaterno;

    private Date fecha_creacion;

    private boolean activo;

    public Cliente() {
    }

    public Cliente( String id, String nombre, String apellido,String apellidoMaterno, Date fecha_creacion, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.apellidoMaterno=apellidoMaterno;
        this.fecha_creacion = fecha_creacion;
        this.activo = activo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fecha_creacion=" + fecha_creacion +
                ", activo=" + activo +
                '}';
    }
}
