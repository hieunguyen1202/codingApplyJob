import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrl: './search.component.scss',
})
export class SearchComponent implements OnInit, OnDestroy {
  //form
  searchForm!: FormGroup;
  advancedSearch: boolean = false;

  constructor(private fb: FormBuilder, private routes: ActivatedRoute) {
    this.searchForm = fb.group({
      textSearch: [''],
      category: ['2'],
      location: ['3'],
      careerLevel: ['3'],
      experience: ['3'],
      offerSalary: ['3'],
      qualification: ['3'],
    });
  }
  ngOnInit(): void {
    this.routes.queryParams.subscribe((params) => {
      console.log('====================================');
      console.log('params::', params);
      console.log('====================================');
    });
  }

  advanced(){
    this.advancedSearch = !this.advancedSearch
    console.log('====================================');
    console.log(this.advancedSearch);
    console.log('====================================');
  }

  submitForm() {
    console.log('====================================');
    console.log('searchForm::', this.searchForm.value);
    console.log('====================================');
  }

  pageActive(pageActive: number): void {
    console.log('====================================');
    console.log('pageActive::', pageActive);
    console.log('====================================');
  }

  ngOnDestroy(): void {}
}
