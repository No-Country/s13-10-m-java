import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { position } from '@models/address.model';
import { LatLng } from 'leaflet';

@Component({
  selector: 'app-modal-register-greenpoint',
  templateUrl: './modal-register-greenpoint.component.html',
  styleUrls: ['./modal-register-greenpoint.component.scss']
})
export class ModalRegisterGreenpointComponent {
  readonly days = ["lunes", "martes", "miercoles", "jueves", "viernes", "sabado", "domingo"]
  readonly recicledTypes = ["Plástico y metal", "Carton y papel", "Vidrio", "General"]

  form !: FormGroup
  constructor(
    private fb:FormBuilder
  ){}
  ngOnInit(){
    this.initForm()
  }

  createGreenpoint(){
    console.log(this.form.value)
  }

  initForm(){
    this.form = this.fb.group({
      name:["", [Validators.required, Validators.minLength(5)]],
      dni:["", [Validators.required, Validators.maxLength(15)]],
      phone:["", [Validators.required, Validators.maxLength(15)]],
      openTime:["08:00", [Validators.required]],
      closeTime:["20:00", [Validators.required]],
      openDay:[0, [Validators.required]],
      closeDay:[6, [Validators.required]],
      type:["General", [Validators.required]],
      address:[""],
      lat:["", [Validators.required]],
      lng:["", [Validators.required]],
    })
  }
  receivePosition(position:position){
    this.form.setValue({
      ...this.form.value,
      ...position
    })
  }

  onlyKeyNumber(event:KeyboardEvent){
    const numberRegex =  /^[0-9]+$/
    if(!numberRegex.test(event.key)){
      event.preventDefault();
    }
  }
}
