<template>
  <main class="tareas">
    <div class="header">
      <h2 class="titulo">Mapa con tareas en Regiones</h2>
      <h3>Seleccione la region para la cual desea ver las tareas</h3>
    </div>
    <SelectField nombre="Regiones" :items="items" @selected="onSelected" />
    <div id="map"></div>
  </main>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import SelectField from "@/components/SelectField.vue";
import L from "leaflet";
import icon from "../assets/location.png";
import { useAuthStore } from "@/stores/auth";
import axios from "axios";
import "leaflet/dist/leaflet.css";

export default defineComponent({
  components: { SelectField },
  name: "TareasPage",
  data() {
    return {
      items: [] as Array<any>,
      points: [] as Array<L.Marker<any>>,
      mymap: null as any,
    };
  },
  computed: {
    myIcon() {
      return L.icon({
        iconUrl: icon,
        iconSize: [32, 32],
        iconAnchor: [12, 41],
        popupAnchor: [5, -41],
      });
    },
  },
  methods: {
    clearMarkers() {
      if (this.mymap) {
        this.points.forEach((p) => {
          this.mymap.removeLayer(p);
        });
        this.points = [];
      }
    },

    setMarkers() {
      this.points.forEach((p) => {
        p.addTo(this.mymap!);
      });
    },

    onSelected(id: number) {
      this.getTareasByRegionId(id);
    },

    getRegiones() {
      const authStore = useAuthStore();
      const token = authStore.token;
      axios
        .get("/api/regiones", {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        })
        .then((response) => {
          this.items = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },

    getTareasByRegionId(id: number) {
      if (this.mymap) {
        const authStore = useAuthStore();
        const token = authStore.token;
        axios
          .get("/api/tareas/gtr/" + id, {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          })
          .then((response) => {
            this.clearMarkers();
            if (response.data && response.data.length != 0) {
              this.points = this.tareasToPoints(response.data);
              this.setMarkers();
              const latlng = this.points[0].getLatLng()
              this.mymap.setView([latlng.lat, latlng.lng], 10)
            }
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },

    tareasToPoints(tareas: Array<any>): Array<L.Marker<any>> {
      let points = [] as Array<L.Marker<any>>;
      tareas.forEach((tarea: any) => {
        const p: [number, number] = [tarea.longitud, tarea.latitud];
        const marker = L.marker(p, { icon: this.myIcon }).bindPopup(tarea.nombre);
        points.push(marker);
      });

      return points;
    },
  },

  mounted() {
    this.mymap = L.map("map").setView([-33.456, -70.648], 10);
    L.tileLayer("http://{s}.tile.osm.org/{z}/{x}/{y}.png", {
      attribution:
        '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors',
      maxZoom: 18,
    }).addTo(this.mymap);

    this.getRegiones();
    // this.mymap.on("click", function (e: any) {
    // console.log(e.latlng.lat);
    // console.log(e.latlng.lng);
    // });
  },
});
</script>
<style>
.tareas {
  display: grid;
  align-items: center;
  justify-items: center;
  margin-top: 1rem;
  gap: 1rem;
  font-family: "Open Sans", sans-serif;
}

.tareas .header .titulo {
  margin-bottom: 1rem;
  font-weight: bold;
  font-size: 1.5rem;
  color: #363225;
  text-align: center;
}

.tareas .header h3 {
  font-size: 1rem;
  font-weight: 500;
  color: #363225;
}

#map {
  height: 680px;
  width: 1400px;
  border-radius: 15px;
  padding-left: 15px;
  padding-right: 15px;
  padding-top: 20px;
  box-shadow: 0rem 0.1rem 0.1rem rgba(0, 0, 0, 0.411);
  margin-bottom: 2rem;

}
</style>
