import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-dropdown-list',
  templateUrl: './dropdown-list.component.html',
  styleUrls: ['./dropdown-list.component.css']
})


export class DropdownListComponent implements OnInit {

  diseases = [];

  selectedDiseaseEan: number;

  constructor() {
    this.create10kDiseases();
  }

  private create10kDiseases() {
    this.diseases = Array.from({length: 10000}, (value, key) => key)
    .map(val => ({
      id: val,
      name: `disease ${val}`
    }));
  }

  ngOnInit(): void {
  }

}
