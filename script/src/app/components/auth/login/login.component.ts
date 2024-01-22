import { Component, HostListener, OnInit } from '@angular/core';
import {
  FormControl,
  FormGroup,
  NonNullableFormBuilder,
  Validators,
} from '@angular/forms';
import { hostname } from 'os';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent implements OnInit {
  backgroundColorNavbar: string = '#e2e8f0';
  textColor: string = 'white';
  boxShadowBottom: string = 'none';

  validateForm: FormGroup<{
    userName: FormControl<string>;
    password: FormControl<string>;
    remember: FormControl<boolean>;
  }> = this.fb.group({
    userName: ['', [Validators.required]],
    password: ['', [Validators.required]],
    remember: [true],
  });

  submitForm(): void {
    console.log('====================================');
    console.log(this.validateForm.value);
    console.log('====================================');
  }

  constructor(private fb: NonNullableFormBuilder) {}

  ngOnInit(): void {
    this.checkWindowScroll();
  }

  @HostListener('window:scroll', ['$event'])
  onScroll(event: Event): void {
    this.checkWindowScroll(); // Check window scroll position
  }

  private checkWindowScroll(): void {
    const scrollY = window.scrollY;
    this.backgroundColorNavbar = scrollY > 0 ? '#e2e8f0' : '#e2e8f0';
    this.textColor = scrollY > 0 ? 'black' : 'black';
    this.boxShadowBottom =
      scrollY > 0 ? '0 4px 8px rgba(0, 0, 0, 0.1)' : 'none';
  }
}
