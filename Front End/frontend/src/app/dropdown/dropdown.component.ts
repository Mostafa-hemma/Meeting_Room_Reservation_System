import { Component } from '@angular/core';

import {  OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { DropdownModule } from 'primeng/dropdown';

interface City {
  name: string;
  code: string;
}
@Component({
  selector: 'app-dropdown',
  templateUrl: './dropdown.component.html',
  styleUrl: './dropdown.component.css',
  standalone: true,
  imports: [FormsModule, DropdownModule]

})
export class DropdownComponent {
  cities: City[] | undefined;

  selectedCity: City | undefined;

  ngOnInit() {
      this.cities = [
          { name: 'New York', code: 'NY' },
          { name: 'Rome', code: 'RM' },
          { name: 'London', code: 'LDN' },
          { name: 'Istanbul', code: 'IST' },
          { name: 'Paris', code: 'PRS' }
      ];
  }
}
