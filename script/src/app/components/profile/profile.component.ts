import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { UploadEvent } from 'primeng/fileupload';
import { Store } from '@ngrx/store';
import { getUser } from '../../shared/login.selector';
import { ProfileService } from '../../services/profile/profile.service';
import { StorageService } from '../../services/storage/storage.service';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { storeUser } from '../../shared/login.action';
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent implements OnInit {
  isSpinningUpdateInfo: boolean = false;
  formProfile!: FormGroup;
  user: any;
  isSpinning: boolean = false;
  checkUserVerify: any;
  email: string = StorageService.getEmail();
  constructor(
    private fb: FormBuilder,
    private messageService: MessageService,
    private store: Store<{ user: any }>,
    private profileService: ProfileService,
    private router: Router,
    private authService: AuthService,
    private msg: NzMessageService
  ) {}

  ngOnInit(): void {
    this.store.select(getUser).subscribe((res) => {
      if (res) {
        this.profileService
          .findAccountByEmail({ email: StorageService.getEmail() })
          .subscribe((res) => {
            console.log('====================================');
            console.log('Profile::', res);
            console.log('====================================');
            this.checkUserVerify = res.metadata;
          });
      }
      this.user = res;
      this.initializeForm();
    });
  }

  initializeForm() {
    this.formProfile = this.fb.group({
      firstName: [this.user?.firstName || '', Validators.required],
      lastName: [this.user?.lastName || '', Validators.required],
      phoneNumber: [this.user?.phoneNumber || '', Validators.required],
      gender: [
        this.user?.gender !== undefined ? this.user?.gender : null,
        Validators.required,
      ],
      address: [this.user?.address || '', Validators.required],
    });
  }

  onUpdateProfile() {
    this.isSpinningUpdateInfo = true;
    console.log('formProfile::', this.formProfile.value);
    const formData = new FormData();
    formData.append('email', StorageService.getEmail());
    formData.append('firstName', this.formProfile.value['firstName']);
    formData.append('lastName', this.formProfile.value['lastName']);
    formData.append('gender', this.formProfile.value['gender']);
    formData.append('phoneNumber', `${this.formProfile.value['phoneNumber']}`);
    formData.append('address', this.formProfile.value['address']);

    this.profileService.updateProfileByEmail(formData).subscribe(
      (res) => {
        this.msg.success('Cập nhập profile thành công');
        this.profileService.findAccountByEmail({ email: this.email }).subscribe(
          (res) => {
            this.profileService
              .viewProfileByEmail({ id: res.metadata.id })
              .subscribe(
                (res) => {
                  this.store.dispatch(storeUser({ user: res.metadata }));
                  this.isSpinningUpdateInfo = false;
                },
                (error) => {
                  this.isSpinningUpdateInfo = false;

                  this.msg.error('Cập nhập profile không thành công', {
                    nzDuration: 2000,
                  });
                }
              );
          },
          (error) => {
            this.isSpinningUpdateInfo = false;

            this.msg.error('Cập nhập profile không thành công', {
              nzDuration: 2000,
            });
          }
        );
      },
      (error) => {
        this.isSpinningUpdateInfo = false;
        if (error?.error?.error) {
          this.msg.error(error?.error?.error, { nzDuration: 2000 });
        } else {
          this.msg.error('Cập nhập profile không thành công', {
            nzDuration: 2000,
          });
        }
      }
    );
  }

  onUpload(event: UploadEvent) {
    console.log('event::', event);
    this.messageService.add({
      severity: 'info',
      summary: 'Success',
      detail: 'File Uploaded with Basic Mode',
    });
  }

  verify() {
    this.isSpinning = true;
    this.authService
      .sendOtpCode({ email: StorageService.getEmail() })
      .subscribe(
        (res) => {
          this.isSpinning = false;
          this.msg.success('Gửi mã xác thực thành công !!!');
          this.router.navigateByUrl('/verifyAccount');
        },
        (error) => {
          this.isSpinning = false;
          if (error?.error?.error) {
            this.msg.error(error?.error?.error, { nzDuration: 2000 });
          } else {
            this.msg.error('Gửi mã xác thực không thành công', {
              nzDuration: 2000,
            });
          }
        }
      );
  }
}
