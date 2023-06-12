<template>
  <div class="input-field">
    <label for="autocomplete">Dirección<br /></label>
    <input id="autocomplete" placeholder="" type="text" ref="origin" />
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { Loader } from "@googlemaps/js-api-loader";
import config from "../../config.json"

export default defineComponent({
  data() {
    return {
      address: "",
    };
  },
  methods: {},
  mounted() {
    const loader = new Loader({
      apiKey: config.GOOGLE_MAPS_API_KEY,
      version: "weekly"
    });

    loader.importLibrary("places").then((places) => {
      const autocompleteElement = this.$refs["origin"] as HTMLInputElement;
      const autocomplete = new places.Autocomplete(autocompleteElement, {
        componentRestrictions: { country: "CL" }
      });
      autocomplete.addListener("place_changed", () => {
        const place = autocomplete.getPlace();
        this.address = place.formatted_address as string;
        if (place.geometry && place.geometry.location) {
          const latitude = place.geometry.location.lat();
          const longitude = place.geometry.location.lng();
          this.$emit("placeData", latitude, longitude)
        }
      });
    });
  },
});
</script>

<style>
.input-field label {
  font-size: 0.9rem;
  font-weight: bold;
  color: #363225;
}

.input-field input {
  border-radius: 0.2rem;
  border: 0.1rem solid #363225;
  background-color: transparent;
  padding: 0.5rem;
  color: #363225;
  width: 25rem;
  outline: none;
  font-family: "Open Sans";
  font-weight: 500;
  color: #363225;
}
</style>