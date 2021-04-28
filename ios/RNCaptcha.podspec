
Pod::Spec.new do |s|
  s.name         = "RNCaptcha"
  s.version      = "1.0.1"
  s.summary      = "RNCaptcha"
  s.description  = <<-DESC
                  RNCaptcha
                   DESC
  s.homepage     = "https://github.com/yidun/captcha-plugin-rn"
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "author@domain.cn" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/yidun/captcha-plugin-rn.git", :tag => "master" }
  s.source_files  = "**/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  s.dependency "NTESVerifyCode"
  #s.dependency "others"

end

  
