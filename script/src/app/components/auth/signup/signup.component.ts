import { Component, HostListener, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  NonNullableFormBuilder,
  ValidatorFn,
  Validators,
} from '@angular/forms';
import { NzFormTooltipIcon } from 'ng-zorro-antd/form';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss',
})
export class SignupComponent implements OnInit {
  backgroundColorNavbar: string = '#e2e8f0';
  textColor: string = 'white';
  boxShadowBottom: string = 'none';

  validateForm: FormGroup<{
    email: FormControl<string>;
    password: FormControl<string>;
    checkPassword: FormControl<string>;
    nickname: FormControl<string>;
    agree: FormControl<boolean>;
  }>;
  captchaTooltipIcon: NzFormTooltipIcon = {
    type: 'info-circle',
    theme: 'twotone',
  };

  submitForm(): void {
    console.log('====================================');
    console.log(this.validateForm.value);
    console.log('====================================');
  }

  updateConfirmValidator(): void {
    /** wait for refresh value */
    Promise.resolve().then(() =>
      this.validateForm.controls.checkPassword.updateValueAndValidity()
    );
  }

  confirmationValidator: ValidatorFn = (
    control: AbstractControl
  ): { [s: string]: boolean } => {
    if (!control.value) {
      return { required: true };
    } else if (control.value !== this.validateForm.controls.password.value) {
      return { confirm: true, error: true };
    }
    return {};
  };

  constructor(private fb: NonNullableFormBuilder) {
    this.validateForm = this.fb.group({
      email: ['', [Validators.email, Validators.required]],
      password: ['', [Validators.required]],
      checkPassword: ['', [Validators.required, this.confirmationValidator]],
      nickname: ['', [Validators.required]],
      agree: [false],
    });
  }

  ngOnInit(): void {
    this.checkWindowScroll();
  }

  @HostListener('window:scroll', ['$event'])
  onScroll(event: Event): void {
    this.checkWindowScroll(); // Check window scroll position
  }

  private checkWindowScroll(): void {
    if (typeof window !== 'undefined') {
      const scrollY = window.scrollY;
      this.backgroundColorNavbar = scrollY > 0 ? '#e2e8f0' : '#e2e8f0';
      this.textColor = scrollY > 0 ? 'black' : 'black';
      this.boxShadowBottom =
        scrollY > 0 ? '0 4px 8px rgba(0, 0, 0, 0.1)' : 'none';
    }
  }
}
